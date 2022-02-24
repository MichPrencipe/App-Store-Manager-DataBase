package appStoreManager.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AppStoreManagerModelImpl implements AppStoreManagerModel {

	private final Connection connection;
	private static final String ERROR = "INPUT ERROR";
	private Statement statement;

	public AppStoreManagerModelImpl() {
		this.connection = new DBConnection().getMySqlConnection();
		this.statement = null;
		try {
			this.statement = this.connection.createStatement();
		} catch (Exception e) {
			System.out.println(ERROR);
		}
	}

	@Override
	public ResultSet executeQuery(int operationNumber, List<String> values) {
		switch (operationNumber) {
		case 1:
			try {
				return this.statement.executeQuery(replaceQuestionMarks(
						"SELECT ap.NomeApp, sum(ac.Prezzo) AS Guadagno FROM Applicazioni ap, Acquisti ac WHERE ac.IdApp = ? AND ap.IdApp = ac.IdApp",
						values));
			} catch (SQLException e) {
				System.out.println(ERROR);
			}
			break;
		case 2:
			try {
				return this.statement.executeQuery(replaceQuestionMarks(
						"SELECT sb.*, v.NumeroVersione,A.nomeApp FROM Segnalazioni_Bug sb, Versioni v, Applicazioni A WHERE sb.IdVersione = ? AND sb.IdVersione = v.idVersione AND v.idApp = a.idApp\r\n" + 
						"",
						values));
			} catch (SQLException e) {
				System.out.println(ERROR);
			}
			break;
		case 3:
			try {
				return this.statement.executeQuery(replaceQuestionMarks(
						"SELECT avg(timestampdiff(YEAR, u.DataNascita, now())) as EtaMedia FROM Utenti u WHERE u.IdUtente IN (SELECT DISTINCT u1.IdUtente FROM utenti u1, download d,dispositivi disp, versioni v, applicazioni a WHERE u1.idUtente = disp.idUtente AND d.idDispositivo = disp.idDispositivo AND d.idVersione = v.idVersione AND v.idApp = a.idApp AND a.idApp = ?)",
						values));
			} catch (SQLException e) {
				System.out.println(ERROR);
			}
			break;
		case 4:
			try {
				return this.statement.executeQuery(replaceQuestionMarks(
						"SELECT app.idApp, app.nomeApp, app.costo, a.nome as nomeAutore FROM Applicazioni app , Autori a WHERE app.IdAutore = ? AND a.IdAutore = app.idAutore",
						values));
			} catch (SQLException e) {
				System.out.println(ERROR);
			}
			break;
		case 5:
			try {
				return this.statement.executeQuery(replaceQuestionMarks(
						"SELECT DISTINCT d.dataDownload, a.nomeApp, v.numeroVersione FROM Download d, Applicazioni a, Versioni v WHERE d.IdDispositivo = ? AND year(d.DataDownload) = '?' AND d.IdVersione = v.IdVersione AND a.IdApp = v.IdApp",
						values));
			} catch (SQLException e) {
				System.out.println(ERROR);
			}
			break;
		case 6:
			try {
				return this.statement.executeQuery(replaceQuestionMarks(
						"SELECT c.Nome, app.NomeApp, app.NumeroDownload FROM Categorie_Applicazione c, Appartenenze a, Applicazioni app WHERE app.idApp = a.idApp AND c.IdCategoria = a.idCategoria AND c.idCategoria = ? ORDER BY app.NumeroDownload DESC LIMIT 3",
						values));
			} catch (SQLException e) {
				System.out.println(ERROR);
			}
			break;
		case 7:
			try {
				return this.statement.executeQuery(replaceQuestionMarks(
						"SELECT ap.NomeApp, ac.Prezzo, ac.Data, ac.Ora FROM Acquisti ac, Applicazioni ap WHERE ac.IdUtente = ? AND ac.IdApp = ap.IdApp",
						values));
			} catch (SQLException e) {
				System.out.println(ERROR);
			}
			break;
		case 8:
			try {
				return this.statement.executeQuery(replaceQuestionMarks(
						"SELECT * FROM Ricerche WHERE IdUtente = ? ORDER BY Data DESC, Ora DESC", values));
			} catch (SQLException e) {
				System.out.println(ERROR);
			}
			break;
		}
		return null;
	}

	@Override
	public int updateQuery(int operationNumber, List<String> values) {
		switch (operationNumber) {
		case 1:
			try {
				return this.statement.executeUpdate(replaceQuestionMarks(
						"INSERT INTO applicazioni VALUES('?', '?', '?', '?', '?', '?', '?', '?', '?', '?', '?', '?', '?')",
						values));
			} catch (SQLException e) {
				System.out.println(ERROR);
			}
			break;
		case 2:
			try {
				return this.statement
						.executeUpdate(replaceQuestionMarks("INSERT INTO versioni VALUES('?', '?', '?', '?')", values));
			} catch (SQLException e) {
				System.out.println(ERROR);
			}
			break;
		case 3:
			try {
				return this.statement.executeUpdate(replaceQuestionMarks(
						"UPDATE Credenziali c, Autori a SET c.Username = '?', c.Password = '?' WHERE c.IdCredenziali = a.IdCredenziali AND a.IdAutore = '?'",
						values));
			} catch (SQLException e) {
				System.out.println(ERROR);
			}
			break;
		case 4:
			try {
				return this.statement.executeUpdate(replaceQuestionMarks(
						"UPDATE Credenziali c, Utenti u SET c.Username = '?', c.Password = '?' WHERE c.IdCredenziali =u.IdCredenziali AND u.IdUtente = '?'",
						values));
			} catch (SQLException e) {
				System.out.println(ERROR);
			}
			break;
		case 5:
			try {
				return this.statement.executeUpdate(
						replaceQuestionMarks("INSERT INTO Recensioni VALUES ('?', '?', '?', '?', '?', '?')", values));
			} catch (SQLException e) {
				System.out.println(ERROR);
			}
			break;
		}
		return -1;
	}

	@Override
	public ResultSet loginAuthorQuery(List<String> values) {
		try {
			return this.statement.executeQuery(replaceQuestionMarks(
					"SELECT * FROM credenziali c, autori a WHERE a.idCredenziali = c.idCredenziali AND a.idAutore = '?' AND c.username = '?' AND c.password = '?'",
					values));
		} catch (SQLException e) {
			System.out.println(ERROR);
		}
		return null;
	}

	@Override
	public ResultSet loginUserQuery(List<String> values) {
		try {
			return this.statement.executeQuery(replaceQuestionMarks(
					"SELECT * FROM credenziali c, utenti u WHERE u.idCredenziali = c.idCredenziali AND u.idUtente = '?' AND c.username = '?' AND c.password = '?'",
					values));
		} catch (SQLException e) {
			System.out.println(ERROR);
		}
		return null;
	}

	private String replaceQuestionMarks(String inputString, List<String> values) {
		String returnString = inputString;
		for (int i = 0; i < values.size(); i++) {
			returnString = replaceFirstOccurrenceOfString(returnString, "?", values.get(i));
		}
		return returnString;
	}

	private String replaceFirstOccurrenceOfString(String inputString, String stringToReplace,
			String stringToReplaceWith) {

		int length = stringToReplace.length();
		int inputLength = inputString.length();

		int startingIndexofTheStringToReplace = inputString.indexOf(stringToReplace);

		String finalString = inputString.substring(0, startingIndexofTheStringToReplace) + stringToReplaceWith
				+ inputString.substring(startingIndexofTheStringToReplace + length, inputLength);

		return finalString;
	}
}