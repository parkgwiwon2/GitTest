package robot;

public class input_thread {


	static Thread eternity = new Thread(){

		public void run() {
			while(!isInterrupted()){
				System.out.println("무한 스레드." + isInterrupted());
				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
					System.out.println("으악 인터럽트됨");
					//return;
				}
			}
			System.out.println("끝남");
		}
	};
	public static void main(String[] args) {

		eternity.start();

		for(int i = 0 ; i < 3 ; i++){

			System.out.println("메인스레드 : " + i);

			try {

				Thread.sleep(1000);

			} catch (InterruptedException e )

			{

				e.printStackTrace();

			}

		}

		eternity.interrupt();

	}


}
