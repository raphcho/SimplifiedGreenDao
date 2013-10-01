
package de.greenrobot.daogenerator.gentest;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Schema;

public class TemplateDaoGenerator {
	
	//static Entity yourModel;	
	
	public static void main(String[] args) throws Exception {
		
		// Change by your informations
		String project = "prettygirls";
		String pkg = "com.afelio";
		// Change de database version here when adding or updating tables
		Integer DatabaseVersion=1;
		
		
		String schemaPath = pkg+"."+project+".entities";
		// Create the Schema
		Schema schema = new Schema(DatabaseVersion, schemaPath);
		
		// Add the database
		// YourModel = schema.addEntity("YourModel");
		// YourModel.implementsSerializable();
		
		
		// Field ID
		// 	YourModel.addIdProperty().columnName("id");
		// 	OR
		// 	YourModel.addIdProperty().autoincrement.columnName("id");
		
		
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
