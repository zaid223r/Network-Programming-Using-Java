public class ThreadShowName extends Thread{

    public static void main(String[] args) {
        ThreadShowName thread1 = new ThreadShowName();
        ThreadShowName thread2 = new ThreadShowName();

        thread1.start();
        thread2.start();
    }

    public void run(){
        int pause;
        for (int i = 0; i < 10 ; i++){
            try {
                System.out.println(getName()+ " being excuted");
                pause = (int) (Math.random()*3000);
                sleep(pause);
            } catch (InterruptedException interruptEX) {
                System.out.println(interruptEX);
            }
        }
    }
}
