package test;

import logic.MyThread;

public class Test {

	private static MyThread th1;
	private static MyThread th2;

	private static Thread th3;
	private static Thread th4;

	public static void test2() {
		th3 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println("Soy el hilo 3 " + i);
				}
				System.out.println(" - - - Hilo 3 Termino la ejecucion - - - ");
			}
		});

		th4 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println(" - - - Soy el hilo 4 " + i + " - - - ");
				}
				System.out.println(" - - - Hilo 4 Termino la ejecucion - - - ");

			}
		});
		try {
			th3.start();
			th3.join();
			th4.start();
			th4.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void test() {
		th1 = new MyThread() {

			@Override
			protected int getSleep() {
				return 5;
			}

			int cont1 = 0;

			@Override
			protected void executeTask() {
				System.out.println("Soy th1 , cont1 " + cont1);
				cont1++;
				if (cont1 > 100) {
					stop();
					System.out.println(" - - - th1 termino ejecucion");
				}

			}
		};

		th2 = new MyThread() {

			@Override
			protected int getSleep() {
				return 5;
			}

			int cont = 0;

			@Override
			protected void executeTask() {
				System.out.println("Soy th2 " + cont);
				cont++;
				if (cont > 100)
					stop();

			}
		};
		th1.start();
		th1.join();
		th2.start();
		th2.join();
	}

	public static void main(String[] args) {
		Test.test2();
	}
}
