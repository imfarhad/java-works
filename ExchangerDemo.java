import java.util.concurrent.Exchanger;

class MakeString implements Runnable{
	Exchanger<String> ex;
	String str;

	MakeString(Exchanger<String> c){
		this.ex = c;
		this.str = new String();
	}

	public void run(){
		char ch = 'A';
		for(int i=0; i<3; i++){

			for(int j=0; j<5; j++){
				str += ch++;
			}

			try{
				str = ex.exchange(str);
			}
			catch(Exception e){

			}			
		}
	}
}

class UseString implements Runnable{
	Exchanger<String> ex;
	String str;

	UseString(Exchanger<String> c){
		this.ex = c;
	}

	public void run(){
		for (int i=0; i<3; i++) {
			try{
				str = ex.exchange(new String("ThankYou"));
				System.out.println("Got: "+str);
			}
			catch(Exception e){

			}
		}
	}
}

class ExchangerDemo{
	public static void main(String[] s){
		Exchanger<String> ex = new Exchanger<String>();
		new Thread(new MakeString(ex)).start();
		new Thread(new UseString(ex)).start();
	}
}