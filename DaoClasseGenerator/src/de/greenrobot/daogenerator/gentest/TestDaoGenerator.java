
package de.greenrobot.daogenerator.gentest;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class TestDaoGenerator {
	
	static Entity Note;	
	
	public static void main(String[] args) throws Exception {
		
		// Change by your informations
		String project = "noteTest";
		String pkg = "com.afelio";
		// Change de database version here when adding or updating tables
		Integer DatabaseVersion=1;
		
		
		String schemaPath = pkg+"."+project+".entities";
		// Create the Schema
		Schema schema = new Schema(DatabaseVersion, schemaPath);
		
		// Add the database
		Note = schema.addEntity("Note");
		Note.implementsSerializable();
		
		
		// Field ID
		// 	YourModel.addIdProperty().columnName("id");
		// 	OR
		Note.addIdProperty().autoincrement().columnName("id");
		Note.addStringProperty("content");
		
		// Fields
		//	STRING
		//	------
		//	YourModel.addStringProperty("property");
		//
		//	BOOLEAN
		//	-------
		//	YourModel.addBooleanProperty("property");
		//
		//	INTEGER
		//	-------
		//	YourModel.addIntProperty("property");
		//
		// 	FLOAT
		//	-----
		//	YourModel.addFloatProperty("property");
		//
		//	DOUBLE
		//	------
		// 	YourModel.addDoubleProperty("property");
		//
		// 	LONG
		//	----
		// 	YourModel.addLongProperty("property");

		
		// Relations
		//	FOREIGN KEY
		//	-----------
		//	Property propertyId = YourModel.addLongProperty("property_id").notNull().getProperty();
		//	YourModel.addToOne(ReferencedEntity, propertyId, "property");
		
		
		new DaoGenerator().generateAll(schema, "./generated-dao");

	}
}
