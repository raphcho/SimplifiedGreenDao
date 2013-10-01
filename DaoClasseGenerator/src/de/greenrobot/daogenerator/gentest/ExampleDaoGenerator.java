/*
 * Copyright (C) 2011 Markus Junginger, greenrobot (http://greenrobot.de)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.greenrobot.daogenerator.gentest;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

/**
 * Generates entities and DAOs for PrettyGirls
 * 
 * Run it as a Java application (not Android).
 */
public class ExampleDaoGenerator {
	
	static Entity	Category;
	static Entity	Comment;
	static Entity	Discussion;
	static Entity	Follower;
	static Entity	Like;
	static Entity	Message;
	static Entity	News;
	static Entity	Notification;
	static Entity	Product;
	static Entity	Topic;
	static Entity	User;
	static Entity	Feed;
	
	public static void main(String[] args) throws Exception {
		String project = "prettygirls";
		String pkg = "be.intotheweb";
		String schemaPath = pkg+"."+project+".entities";
		Integer DatabaseVersion=1;
		Schema schema = new Schema(DatabaseVersion, schemaPath);
		
		
		Category = schema.addEntity("Category");
		Comment = schema.addEntity("Comment");
		Discussion = schema.addEntity("Discussion");
		Follower = schema.addEntity("Follower");
		Like = schema.addEntity("Like");
		Message = schema.addEntity("Message");
		News = schema.addEntity("News");
		Notification = schema.addEntity("Notification");
		Product = schema.addEntity("Product");
		Topic = schema.addEntity("Topic");
		User = schema.addEntity("User");
		
		addCommonsProperties(schema);
		
		addCategory(schema);
		addComment(schema);
		addDiscussion(schema);
		addFollower(schema);
		addLike(schema);
		addMessage(schema);
		addNews(schema);
		addNotification(schema);
		addProduct(schema);
		addTopic(schema);
		addUser(schema);
		
		new DaoGenerator().generateAll(schema, "./generated-dao");
	}
	
	private static void addCommonsProperties(Schema schema) {
		addDefaults(Category);
		addDefaults(Comment);
		addDefaults(Discussion);
		addDefaults(Follower);
		addDefaults(Like);
		addDefaults(Message);
		addDefaults(News);
		addDefaults(Notification);
		addDefaults(Product);
		addDefaults(Topic);
		addDefaults(User);
	}
	
	private static void addDefaults(Entity entity) {
		entity.addIdProperty().autoincrement().columnName("id");
		entity.addStringProperty("deleted_at");
		entity.addStringProperty("updated_at");
		entity.addStringProperty("created_at");
		entity.implementsSerializable();
	}
	
	private static void addCategory(Schema schema) {
		Category.addStringProperty("name").notNull();
		Property parentId = Category.addLongProperty("parent_id").getProperty();
		Category.addToOne(Category, parentId);
	}
	
	private static void addComment(Schema schema) {
		Property userId = Comment.addLongProperty("user_id").notNull().getProperty();
		Comment.addToOne(User, userId);
		Comment.addStringProperty("content");
		Comment.addLongProperty("commentable_id");
		Comment.addStringProperty("commentable_type");
	}
	
	private static void addDiscussion(Schema schema) {
		Property topicId = Discussion.addLongProperty("topic_id").notNull().getProperty();
		Discussion.addToOne(Topic, topicId);
		
		Property messageID = Discussion.addLongProperty("message_id").notNull().getProperty();
		Discussion.addToOne(Message, messageID);
		
		Discussion.addBooleanProperty("seen");
	}
	
	private static void addFollower(Schema schema) {
		Property followerId = Follower.addLongProperty("follower_id").notNull().getProperty();
		Follower.addToOne(User, followerId, "follower");
		
		Property userId = Follower.addLongProperty("user_id").notNull().getProperty();
		Follower.addToOne(User, userId, "user");
	}
	
	private static void addLike(Schema schema) {
		Property userId = Like.addLongProperty("user_id").notNull().getProperty();
		Like.addToOne(User, userId);
		
		Like.addIntProperty("likable_id");
		Like.addStringProperty("likable_type");
	}
	
	private static void addMessage(Schema schema) {
		Property userId = Message.addLongProperty("user_id").notNull().getProperty();
		Message.addToOne(User, userId);
		
		Message.addStringProperty("content");
	}
	
	private static void addNews(Schema schema) {

	}
	
	private static void addNotification(Schema schema) {
		
		Property userId = Notification.addLongProperty("user_id").notNull().getProperty();
		Notification.addToOne(User, userId, "user");
		
		Property authorId = Notification.addLongProperty("author_id").notNull().getProperty();
		Notification.addToOne(User, authorId, "author");
		
		Notification.addLongProperty("notifiable_id");
		Notification.addStringProperty("notifiable_type");
		Notification.addBooleanProperty("seen");
	}
	
	private static void addProduct(Schema schema) {
		
		Property userId = Product.addLongProperty("user_id").notNull().getProperty();
		Product.addToOne(User, userId);
		
		Property categoryId = Product.addLongProperty("category_id").notNull().getProperty();
		Product.addToOne(Category, categoryId);
		
		Product.addStringProperty("name");
		Product.addStringProperty("content");
		Product.addBooleanProperty("available");
		Product.addStringProperty("photo");
	}
	
	private static void addTopic(Schema schema) {
		Property userId = Topic.addLongProperty("user_id").notNull().getProperty();
		Topic.addToOne(User, userId);
	}
	
	private static void addUser(Schema schema) {
		
		User.addStringProperty("email");
		User.addStringProperty("firstname");
		User.addStringProperty("lastname");
		User.addStringProperty("photo");
		User.addStringProperty("cover");
		User.addStringProperty("bio");
		User.addStringProperty("adress");
		User.addStringProperty("city");
		User.addStringProperty("zipcode");
		User.addStringProperty("last_sign_in_at");
		User.addStringProperty("current_sign_in_at");
	}
}
