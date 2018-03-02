public class Red_Black_Tree{
    private Node root;	// the properties of the tree
    private Node median; //pointer to median
    private int size;

    // constructor. Complexity: theta(1)
    public Red_Black_Tree(){
        root = null;
        median = null;
        size=0;
    }
    // method which returns the root of the tree. Complexity: theta(1)
    public Node getRoot(){
        return root;
    }
    // method which returns the value of the median. Complexity: theta(1)
    public double printMedian(){
        if(median!=null)
            return median.getValue();
        System.out.println("It is empty!");
        return 0.0;
    }
    // method which inserts values into the tree, the method was built according to algorithm in the book. Complexity: theta(log n). page 236
    public void insert(double value){
        Node z=new Node(value,null);
        size++;		// updates the size
        if (root==null) {
            z.setColor('b');
            root = z;
            median=z;
        }
        else{	// if the tree isn't empty
            Node y = null;
            Node x = root;
            while (x != null) {//finds the place to z
                y = x;
                if (value < x.getValue())
                    x = x.getLeft();
                else
                    x = x.getRight();
            }
            z.setParent(y);
            if(value<y.getValue())
                y.setLeft(z);
            else
                y.setRight(z); // saves the correct structure
            fix_Median(value); //moves the median pointer to the right new median
            insert_FixUp(z); // makes it RBT
        }
    }
    //method which gets a new Value in the tree and updates the previous median to the current median. Complexity: O(log n)
    private void fix_Median(double newValue){
        // if the size is even and the new Value is smaller than the previous median so the current median would be the predecessor node of median
        if(size%2==0 && median.getValue() > newValue)
            median = tree_Predecessor(median);
        // if the size is odd and the new Value is bigger than the previous median so the current median would be the successor node of median
        else if(size%2==1 && median.getValue() < newValue)
            median = tree_Successor(median);
        // else, the median wouldn't change
    }
    // finds the minimum in the sub-tree of the node according to algorithm in the book. complexity: O(log n)
    private Node tree_Min(Node x){
        while(x.getLeft()!=null) // finds the most left leaf
            x=x.getLeft();
        return x;
    }
    // finds the maximum in the sub-tree of the node according to algorithm in the book. complexity: O(log n)
    private Node tree_Max(Node x){
        while(x.getRight()!=null) // finds the most right leaf
            x=x.getRight();
        return x;
    }
    //method which gets a node and returns the successor node according to algorithm in the book. complexity: O(log n). page 218
    private Node tree_Successor(Node x){
        if(x.getRight()!=null) // finds the most right leaf
            return tree_Min(x.getRight());
        Node y=x.getParent();
        while(y!=null && x==y.getRight()){
            x = y;
            y = y.getParent();
        }
        return y;
    }
    // method which gets a node and returns the predecessor node. complexity: O(log n)
    private Node tree_Predecessor(Node x){
        if(x.getLeft()!=null) // finds the most left leaf.
            return tree_Max(x.getLeft());
        Node y=x.getParent();
        while(y!=null && x==y.getLeft()){
            x = y;
            y = y.getParent();
        }
        return y;
    }
    public void preorder_Tree_Walk(){
        preorder_Tree_Walk(root);
    }
    private void preorder_Tree_Walk(Node x){
        if(x!=null) {
            System.out.print(x+"\n");
            preorder_Tree_Walk(x.getLeft());
            preorder_Tree_Walk(x.getRight());
        }
    }
    // method which rotates (to the left) part of the tree according to the book. complexity: theta(1) page 234
    private void left_Rotate(Node x){
        Node y=x.getRight();
        x.setRight(y.getLeft()); // turns the left subtree of y into the right subtree of x
        if(y.getLeft()!=null)
            y.getLeft().setParent(x);
        y.setParent(x.getParent()); // links
        if(x.getParent()==null)
            root=y;
        else if (x==x.getParent().getLeft())
            x.getParent().setLeft(y);
        else
            x.getParent().setRight(y);
        y.setLeft(x); //sets the left son of y to be x
        x.setParent(y);
    }
    // method which rotates (to the left) part of the tree according to the guide book. complexity: theta(1) page 186
    private void right_Rotate(Node x){
        Node y=x.getLeft();
        x.setLeft(y.getRight()); // turns the right subtree of y into the left subtree of x
        if(y.getRight()!=null)
            y.getRight().setParent(x);
        y.setParent(x.getParent()); // links
        if(x.getParent()==null)
            root=y;
        else if (x==x.getParent().getRight())
            x.getParent().setRight(y);
        else
            x.getParent().setLeft(y);
        y.setRight(x); //sets the right son of y to be x
        x.setParent(y);
    }
    // method which saves the attributes of RB-tree according to the algorithm in the book. complexity: O(log n). page 236
    private void insert_FixUp(Node z){
        Node y;
        while (z!=root && z.getParent().getColor()=='r'){//while the parent is red and could be a problem
            if(z.getParent()==z.getParent().getParent().getLeft()) { // if the parent of z is left son
                y = z.getParent().getParent().getRight(); // the uncle
                if (y!=null && y.getColor() == 'r') {
                    z.getParent().setColor('b'); //case 1
                    y.setColor('b');
                    z.getParent().getParent().setColor('r');
                    z = z.getParent().getParent();
                }
                else{ // case 2
                    if (z == z.getParent().getRight()) {
                        z = z.getParent();
                        left_Rotate(z);
                    }
                    z.getParent().setColor('b'); // case 3
                    z.getParent().getParent().setColor('r');
                    right_Rotate(z.getParent().getParent());
                }
            }
            else{ // if the parent of z is right son
                y = z.getParent().getParent().getLeft();// the uncle
                if (y!=null && y.getColor() == 'r') {
                    z.getParent().setColor('b'); //case 1
                    y.setColor('b');
                    z.getParent().getParent().setColor('r');
                    z = z.getParent().getParent();
                }
                else{ // case 2
                    if (z == z.getParent().getLeft()) {
                        z = z.getParent();
                        right_Rotate(z);
                    }
                    z.getParent().setColor('b'); // case 3
                    z.getParent().getParent().setColor('r');
                    left_Rotate(z.getParent().getParent());
                }
            }
        }
        root.setColor('b'); // the root's color always should be black
    }
}