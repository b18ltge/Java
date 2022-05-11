package Misc.L10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MyDataBase {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private int maxId;
	public int getMaxId() {
		return maxId;
	}
	
	private void updateMaxId() {
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select max(id) from parts;");
	        while ( result.next() ) {
	       	 	maxId = result.getInt("max");
	        }
		} catch (SQLException e) {
			return;
		}
        
	}
	
	public MyDataBase() {
		try {
	         Class.forName("org.postgresql.Driver");
	         connection = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Java10_1",
	            "postgres", "Agent023");
	         connection.setAutoCommit(true);
	         updateMaxId();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println("Не вдалось підключитись до Бази Даних");
	         System.exit(0);
	      }
	}
	
	public void close() {
		try {
			resultSet.close();
			statement.close();
			connection.commit();
			connection.close();
		}
		catch(Exception e) {
			return;
		}
	}
	
	// it doesn't work :(
	@Override
	protected void finalize() {
		try {
			statement.close();
			connection.commit();
			connection.close();
		}
		catch(Exception e) {
			return;
		}
	}

	@SuppressWarnings("finally")
	public ObservableList<CarPart> getData() {
		LinkedList<CarPart> list = new LinkedList<CarPart>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from parts;");

	        while ( resultSet.next() ) {
	        	list.add(new CarPart(resultSet.getInt("Id"),resultSet.getString("name"),resultSet.getDate("date").toLocalDate(),
							resultSet.getFloat("price"),resultSet.getShort("count")));
	        }
		} catch(Exception e) {
			System.out.print(e.getMessage());
		} finally {
			return FXCollections.observableList(list);
		}
	}
	
	public boolean insert(String name, LocalDate date, float price, int count) {
		if (name == null || name.length() < CarPart.MIN_NAME_LENGTH || name.length() > CarPart.MAX_NAME_LENGTH)
			return false;
		else if (date == null || date.isAfter(LocalDate.now()) || date.isBefore(CarPart.MIN_DATE))
			return false;
		else if (price <= 0 || price > CarPart.MAX_PRICE)
			return false;
		else if (count < 0 || count > Short.MAX_VALUE)
			return false;
		
		try {
			statement = connection.createStatement();
			String sql = "insert into parts (id,name,date,price,count) "
			          + "VALUES (" + ++maxId + ",'" + name + "','" + date + "'," + price + "," + count + ");";
			statement.executeUpdate(sql);
		} catch (Exception e) {
			//System.err.println("Не вдалось");
			//System.out.println(e.getMessage());
			return false;
		}
		//System.out.println("ОК");
		updateMaxId();
        return true;
	}

	public <MyType> boolean update(int id, MyType value) {
		if (id < 1 || id > maxId)
			return false;
		
		if (value instanceof String) {
			try {
				statement = connection.createStatement();
				String sql = "update parts set name = '"+ value +"' where id = " + id;
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				return false;
			}
		}
		else if (value instanceof LocalDate) {
			try {
				statement = connection.createStatement();
				String sql = "update parts set date = '"+ value +"' where id = " + id;
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				return false;
			}
		}
		else if (value instanceof Float) {
			try {
				statement = connection.createStatement();
				String sql = "update parts set price = "+ value +" where id = " + id;
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				return false;
			}
		}
		else if (value instanceof Short) {
			try {
				statement = connection.createStatement();
				String sql = "update parts set count = "+ value +" where id = " + id;
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				return false;
			}
		}
		else return false;
		return true;
	}

	public boolean delete(int id) {
		if (id < 1 || id > maxId)
			return false;
		
		try {
			statement = connection.createStatement();
			String sql = "delete from parts where id = " + id;
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			return false;
		}
		
		updateMaxId();
		return true;
	}

	public <MyType> ObservableList<CarPart> find(MyType value) {	
		LinkedList<CarPart> list = new LinkedList<CarPart>();
		if (value instanceof String) {
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery( "select * from parts where name like '%" + value + "%';");
				while ( resultSet.next() ) {
		        	list.add(new CarPart(resultSet.getInt("Id"),resultSet.getString("name"),resultSet.getDate("date").toLocalDate(),
								resultSet.getFloat("price"),resultSet.getShort("count")));
		        }
			} catch (SQLException e) {
				System.out.print(e.getMessage());
			} finally {
				return FXCollections.observableList(list);
			}
		}
		return null;
		/*else if (value instanceof LocalDate) {
			try {
				
			} catch (SQLException e) {
				return false;
			}
		}
		else if (value instanceof Double) {
			try {
				
			} catch (SQLException e) {
				return false;
			}
		}
		else if (value instanceof Integer) {
			try {
				
			} catch (SQLException e) {
				return false;
			}
		}*/
	}

}
