
package simulation;
import java.util.Random;
/**
 * @author KROUMI DHAKER
 * @version 24 August 2020
 */
public class RandomGraph {
    
    /**
    * cette methode permet de creer un graph connecte et de degree en moyenne 
    *  "degree"
    * @param length
    * @return the adjacency matrix of the graph
    */
    public static int[][] cycle ( int length) {
      
        int[][] AM = new int[length][length];
        
       
        for (int i = 0 ; i < length-1; i++){
            AM[i][i+1]=AM[i+1][i]=1;
        }
        AM[length-1][0]=AM[0][length-1]=1;
        // we are sure that the graph is connected with length nodes
        return AM;
    }
    
    
     /**
    * cette methode permet de creer un graph connecte et de degree en moyenne 
    *  "degree"
    * @param length
    * @return the adjacency matrix of the graph
    */
    public static int[][] randRegGraphDegreeThree ( int length) {
      
        int[][] AM = new int[length][length];
        int numPairs = length/2;
        
        AM[0][1]=AM[1][0]=AM[0][2]=AM[2][0]=1;
       
        for (int i = 0 ; i < numPairs-1; i++){
            AM[2*i+1][2*i+3]=AM[2*i+3][2*i+1]= AM[2*i+1][2*i+2]=AM[2*i+2][2*i+1]=1;
        }
        
        for (int i = 1 ; i < numPairs-1; i++){
            AM[2*i][2*i+2]=AM[2*i+2][2*i]=1;
        }
        AM[length-2][length-1]=AM[length-1][length-2]=
                AM[length-1][0]=AM[0][length-1]=1;
        // we are sure that the graph is connected with length nodes
        return AM;
    }
    
    /**
    * cette methode permet de creer un graph connecte et de degree en moyenne 
    *  "degree"
    * @param length
    * @return the adjacency matrix of the graph
    */
    public static int[][] cycleDegreeThree ( int length) {
      
        int[][] AM = new int[length][length];
        int choix = tirerUnNombre(2);
        
        for (int i = 0 ; i < length-1; i++){
            AM[i][i+1]=AM[i+1][i]=1;
        }
        AM[length-1][0]=AM[0][length-1]=1;
        
        
        if (length%2==0){
            if (choix == 0){
                for (int i = 0; i < (length/2)-1 ; i++){
                     AM[i][i+(length/2)]=AM[i+(length/2)][i]=1;
                }
            } else {
               for (int i = 0; i < (length/2)-1 ; i+=2){
                     AM[i][i+2]=AM[i+2][i]=1;
                } 
            }   
        } 
        
        
        
        // we are sure that the graph is connected with length nodes
        return AM;
    }
    
    /**
    * cette methode permet de creer un graph connecte et de degree en moyenne 
    *  "degree"
    * @param degree
    * @param length
    * @return the adjacency matrix of the graph
    */
    public static int[][] generalRandomGraph (int degree, int length) {
      
        int[][] AM = new int[length][length];
        int choix1;
        int choix2;
       
        AM[0][1]=AM[1][0]=1;
        for (int i = 2 ; i < length; i++){
            choix1 = tirerUnNombre(i);
            AM[i][choix1]=AM[choix1][i]=1;
        }
        // we are sure that the graph is connected with length nodes
        
        for (int t=0 ; t < length*degree/2-length ; t++) {

	    choix1 = tirerUnNombre(length);
            choix2 = tirerUnNombre(length);

	    while (AM[choix1][choix2]== 1 || choix1 == choix2) {
                choix1 = tirerUnNombre(length);
                choix2 = tirerUnNombre(length);
            }

	     AM[choix1][choix2]=AM[choix2][choix1]=1;

        }   
        return AM;
    }
    
    /**
    * cette methode permet de creer un graph connecte et de degree en moyenne 
    *  "degree"
    * @param degree
    * @param length
    * @return the adjacency matrix of the graph
    */
    public static int[][] randomRegularGraph (int degree, int length) {
      
         int[][] AM = new int[length][length];
         int[] sq = new int[length];
         int r;
         
         
      do {  
        for (int p=1 ; p <= degree ; p++) {

	    for (int i=0 ; i < length ;i++){
                sq[i]=i;
            }

	     shuffleArray(sq);

	     for (int i=0 ; i < length ; i+=2) {

		if (AM[sq[i]][sq[i+1]] == 1) {

			r = tirerUnNombre(length/2)*2;

			while (AM[sq[r]][sq[i+1]]==1 || AM[sq[i]][sq[r+1]]==1){
                           r = tirerUnNombre(length/2)*2;
                        }

		     swap(sq, sq[i], sq[r]);
                }
             }

	     for (int i=0; i < length;i+= 2) {
                 AM[sq[i]][sq[i+1]]=1;
                 AM[sq[i+1]][sq[i]]=1;
            }

        }
      } while (checkConnectivity(length,AM));  
      return AM;
    }
    
    /* This method allow us to place a cooperator in a random position in 
         the graph
    **/
    public static int [] constructSitesOneCoop(int length) {
      
        int[] sites = new int[length] ;
        int pos;
        pos = RandomGraph.tirerUnNombre(length);
        sites [pos] = 1;
        return sites;
    }
    
    /* This method allow us to place a defector in a random position in 
         the graph
    **/
    public static int [] constructSitesOneDef(int length) {
      
        int[] sites = new int[length] ;
        int pos;
        
        for (int i=0 ; i<length ; i++){
            sites[i] =1 ;
        }
        pos = tirerUnNombre(length);
        sites [pos] = 0;
           
        return sites;
    }
    
   /**
    * Retourne un nombre entier tire au hasard entre 0 (inclus) et max (exclus)
    * @param max la borne maximale du nombre a tirer.
    * @return un entier tire au hasard entre min et max inclusivement.
    */
    public static int tirerUnNombre( int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }
    
    public static void shuffleArray(int[] a) {
        int n = a.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(a, i, change);
        }
    }
    
    private static void swap(int[] a, int i, int change) {
        int helper = a[i];
        a[i] = a[change];
        a[change] = helper;
    }
    
    
    public static boolean checkConnectivity(int N, int [][] AM){

	 int[] s = new int[N];
         int[] t = new int[N+1];
         int sum=0; 
         int tail=1;
         int i;

	 s[0]=1;
         t[0]=0;

	for (i=1 ; i < N ; i++) {
            t[i]=-1;
            s[i]=0;
        }

	while (sum<N && sum<tail) {
            for (i=0 ; i<N ; i++) {
                if (AM[t[sum]][i]== 1 && s[i]==0) {
                    s[i]=1; t[tail]=i; tail++;
                }
            }
            sum++;
        }
        return sum == N;
    }
}