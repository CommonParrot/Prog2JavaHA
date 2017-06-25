package de.tuberlin.snet.prog2.ue09.task2and3;

public class ScalaToJava{

	// 1
	class Hello{
	}

	// 2
	public final static class World {
		private static final World instance = new World();

		private World() {
		}

		public static World getInstance() {
			return instance;
		}
	}

	// 3
	class User {

		String name;

		public User(String n) {
			this.name = n;
		}

		public void greet() {
			System.out.println("Hello from " + this.name);
		}

		@Override
		public String toString() {
			return this.name;
		}

	}

	// 4
	class Person {

		String name = "";
		private int age = 0;

		public Person(String n) {
			this.name = n;
		}

		public Person(String n, int a) {
			this.name = n;
			this.age = a;
		}

	}

	// 5
	class Person2 {

		String name;
		int age;

		public Person2(String n, int a) {
			this.name = n;
			this.age = a;
		}
	}

}
