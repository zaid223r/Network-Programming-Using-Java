public class RunnableShowName implements Runnable{
    public static void main(String[] args) {
        
        Thread thread1 = new Thread(new RunnableShowName());
        Thread thread2 = new Thread(new RunnableShowName());
        
        thread1.start();
        thread2.start();
    }

    public void run(){
        int pause;
        for (int i = 0; i < 10;i++){
            try {
                System.out.println(Thread.currentThread().getName()+" being excuted");
                pause = (int) (Math.random()*3000);
                Thread.sleep(pause);
            } catch (InterruptedException interruptEX) {
                System.out.println(interruptEX);
            }
        }
    }
}
