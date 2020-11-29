
package simulation;

/**
 * Define a type : Graph, which is characterized by population size (size),
 * the payoffs (R,S,T,P), the selection intensity (delta), the aspiration levels
 * (alphaCoop and alphaDef), the degree of the graph (degree), number of cooperators, winGame, 
 * lose game, sites, edges (adjacency matrix), nbrFixation of cooperators,
 * and number of games (nbrGames)
 * @author (KROUMI DHAKER)
 * @version (14 AUGUST 2020)
 */
public class Graph{
    // instance variables - replace the example below with your own
    private static  int size;
    private static  double R;
    private static double S;
    private static double T;
    private static double P;
    private static double delta;
    private static double alphaCoop;
    private static double alphaDef;
    private static int degree;
    private static int numberCooperators;
    private static boolean winGame;
    private static boolean loseGame;
    private static int [] sites;
    private static int [][] edges;
    
    

    /**
     * Constructor for objects of class Graph
     * @param length number of sites on the graph
     * @param deg number of connections of each site
     * @param nbrCoop number of cooperators in the population
     * @param R
     * @param S
     * @param T
     * @param P
     * @param alphaC
     * @param alphaD
     * @param delta
    */
    public Graph(int length, int deg, int nbrCoop, double R,
            double S, double T, double P, double alphaC,double alphaD, double delta){
        
        Graph.size = length;
        Graph.degree = deg;
        Graph.numberCooperators = nbrCoop;
        Graph.winGame = false;
        Graph.loseGame = false;
        Graph.edges = RandomGraph.randRegGraphDegreeThree(size);
        Graph.R = R;
        Graph.S = S;
        Graph.T = T;
        Graph.P = P;
        Graph.alphaCoop = alphaC;
        Graph.alphaDef = alphaD;
        Graph.delta = delta;
        Graph.sites = RandomGraph.constructSitesOneCoop(size);
        //this.edges = Service.formVerteces(sites ,length , degree);
    }
    
    
    /**
     * cette methode permet de reinitialiser un jeu
     * 
    */
    public void reinitialiserGraph(){
        
        Graph.winGame = false;
        Graph.loseGame = false;
        Graph.numberCooperators = 1;
        Graph.sites =  RandomGraph.constructSitesOneCoop(size);
     }
    
    /**
     * this method calculate the payoff of the different individuals
     * @param pos
     * @return an array contains the different payoffs 
     */
    public double payoff(int pos){
        
        int degreeOfInd;
        double tab;
        
        tab= 0;
        degreeOfInd = 0;
        for (int j = 0 ; j < size ; j++){
            if (pos != j && edges[pos][j] == 1){
                degreeOfInd++;
                if (sites[pos] ==1 && sites[j]==1){
                    tab = tab + R;
                } else if (sites[pos] ==1 && sites[j]==0){
                    tab = tab + S;
                }  else if (sites[pos] ==0 && sites[j]==1){
                    tab = tab + T;
                }  else if (sites[pos] ==0 && sites[j]==0){
                    tab = tab + P;
                } 
            }   
        }    
            tab = tab/degreeOfInd;
        
        return tab;
    }
    
    
    /**
     * this method select a random number from 0 to length-1,
     * the position of individual who will update its strategy
     * @return 
     */
    public int select(){
        return RandomGraph.tirerUnNombre(size);
    } 
    
    /**
     * @param pos
     * @return true if the individual will update its strategy, false if it 
     *  will keep its strategy
     
     */
    public boolean decide( int pos){
        double prob;
        boolean change;
        double choice;
        if (sites[pos]==1){
            prob = 1/(1 + Math.exp(delta*(payoff(pos)-alphaCoop)));
        } else{
            prob = 1/(1 + Math.exp(delta*(payoff(pos)-alphaDef)));
        }
        
        choice = Math.random();
       
        change = choice < prob;    
        return change; 
    }
    
    /**
     * find the connectivity of the individual located at the position position
     * @param position the position of the individual
     * @return  the connectivity
     */
    public int degreeOfIndividual(int position){
        int deg = 0;
        
        for (int i = 0; i < size; i++){
            if (edges[position][i] == 1 && i != position){
                deg++;
            } 
        }       
        return deg;
    }
    
    /**
     * find all the neighbors of the individual located at position
     * @param position the position of the individual
     * @return  a vector of the positions of its neighbors
     */
    public double [] fitnessesNeighbors(int position){
        int deg = degreeOfIndividual(position);
        double [] tab = new double [3];
        double sumCoop = 0;
        double sumDef = 0;
        double sum = 0;
        
        for (int i = 0; i < deg; i++){
            if (sites[tabNeighbors(position)[i]] == 1){
                sumCoop = sumCoop + 1+ delta*payoff(tabNeighbors(position)[i]);
            } else {
                sumDef = sumDef + 1+ delta*payoff(tabNeighbors(position)[i]);
            }
        }
        tab[0] = sumCoop;
        tab[1] = sumDef;
        tab[2]= sumCoop+sumDef;
        return tab; 
    }
    
    /**
     * find all the neighbors of the individual located at position
     * @param position the position of the individual
     * @return  a vector of the positions of its neighbors
     */
    public int [] tabNeighbors(int position){
        int deg = degreeOfIndividual(position);
        int [] tab = new int [deg];
        int pos = 0; // the position of the neighbor
        
        for (int i = 0; i < size; i++){
            if (edges[position][i] == 1){
                tab[pos] = i;
                pos++;
            } 
        }       
        return tab; 
    }
    
    
    /**
     * find all the neighbors of the individual located at position
     * @param position the position of the individual who will update its
     * strategy
     */
    public  void update (int position){
        
        double choix=Math.random();
        double proCoop = fitnessesNeighbors(position)[0]/fitnessesNeighbors(position)[2];
        
        
        
        if (choix <= proCoop && sites[position] == 0){
             sites[position]=1;
             numberCooperators++;
             
        } else if (choix > proCoop && sites[position] == 1){
             sites[position]=0;
             numberCooperators--;
        }     
        
         if (numberCooperators == size){
            winGame = true;
         } else if (numberCooperators == 0){
            loseGame = true;
         }
    }
    
    /**
     * The length of the graph, number of sites on G
     * @return taille de la population
     */
    public int getSize (){
        return size;
    } 
    
    /**
     * The degree of the graph, number of connections of any site
     * @return  degree du graphe
     */
    public int getDegree (){
        return degree;
    }    
     
    
    /**
     * The number of cooperators in the graph G
     * @return nombre de cooperateurs
     */
    public int getNumberCooperators(){
        return numberCooperators;
    } 
    
     /** 
     * @return  un vecteur contenant le type de chaque individu
     */
    public int []  getSites (){
        return sites;
    }  
    
    /** 
     * @return  la matrice d'adjacence 
     */
    public int [][]  getEdges (){
        return edges;
    }  
    
   
    /**
     * Cette methode permet de consulter l'attribut "la partie est gagnee"
     * @return boolean: true si la partie est gagnee, false si la partie n'est 
     * pas encore gagnee
    */
    public boolean isPartieGagnee() {
        return winGame;
    }
    
    /**
     * Cette methode permet de consulter l'attribut "la partie est perdue"
     * @return boolean: true si la partie est perdue, false si la partie n'est 
     * pas encore perdue
    */
    public boolean isPartiePerdue() {
        return loseGame;
    }
    
}

