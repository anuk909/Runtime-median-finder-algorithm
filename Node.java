public class Node {
    private double value; // the properties of a node in RBT
    private Node parent;
    private Node left;
    private Node right;
    private boolean color;//false=Red,true=Black

    // constructor. Complexity: theta(1)
    public Node(double value,Node parent){ 
        this.value = value;
        this.parent=parent;
        left=null;
        right=null;
        color=false; // red
    }
    
    // method which returns the value. Complexity: theta(1)
    public double getValue(){ 
        return value;
    }

    // method which sets the value to the given integer. Complexity: theta(1)
    public void setValue(double value){
        this.value = value;
    }

    // method which returns the parent. Complexity: theta(1)
    public Node getParent() {
        return parent;
    }

    // method which sets the parent to the given node. Complexity: theta(1)
    public void setParent(Node parent){
        this.parent = parent;
    }

    // method which returns the left. Complexity: theta(1)
    public Node getLeft(){
        return left;
    }

    // method which sets the left to the given node. Complexity: theta(1)
    public void setLeft(Node left){
        this.left = left;
    }

    // method which returns the right. Complexity: theta(1)
    public Node getRight() {
        return right;
    }

    // method which sets the right to the given node. Complexity: theta(1)
    public void setRight(Node right){
        this.right = right;
    }

    // method which returns the color. Complexity: theta(1)
    public char getColor(){
        if(color)
            return 'b';
        return 'r';
    }

    // method which sets the color to the given color. Complexity: theta(1)
    public void setColor(char color){
        this.color=(color == 'b');
    }

    @Override
    //to delete
    public String toString() {
        double parentVal=(parent==null)?0:parent.getValue();
        double leftVal=(left==null)?0:left.getValue();
        double rightVal= (right==null)? 0:right.getValue();
        String str="value= "+value+" parent= "+parentVal+
                " Left= "+leftVal+" Right= "+rightVal+" Color= ";
        if(color)
            str+="Black";
        else
            str+="Red";
        return str;
    }
}