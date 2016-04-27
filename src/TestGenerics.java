//I    
interface Test{
	  //mI
    	public void display();
    }
    
    public class TestGenerics implements Test{

    	//mC
	@Override
	public <T> void display() {
		System.out.println("done");
	}
	
	public static void main(String args[]){
		TestGenerics ts = new TestGenerics();
		ts.display();
	}
    }

// Overriding

