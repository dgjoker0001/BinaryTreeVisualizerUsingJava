package org.joker;

import java.util.*;

public class Main {

    //    Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println(" ");
        System.out.println("1. Enter the nodes separated by space");
        System.out.println("2. Nodes should be entered from top to bottom, from left to right");
        System.out.println("3. If a branch is ending, enter null");
        System.out.println("4. Press enter when done");
        System.out.print("5. Start Input: ");
        String tree = sc.nextLine(); // get tree input
        System.out.println(" ");
        if(tree==null || tree.isEmpty()){
            System.out.println("No data entered"); // empty input check
        }
        else{
            tree = tree.replaceAll("\\s{2,}", " "); // remove all extra space
            List<String> treeNodes = new ArrayList<>(List.of(tree.split(" "))); // split input into list

            if(treeNodes.isEmpty()){
                System.out.println("No data entered"); // empty input check
            }
            else{
                Node head = buildTree(treeNodes); // build tree
                System.out.println("  ");
                System.out.println("Tree is built"); // tree built confirmation
                System.out.println(" ");
                assert head != null; // head null check
                printTree(head, "", true, false); // print tree
                System.out.println(" ");
                System.out.println("Tree is printed"); // tree printed confirmation
            }
        }
    }

    //    Build tree from list of nodes
    public static Node buildTree(List<String> treeNodes){
        if ("NULL".equalsIgnoreCase(treeNodes.get(0))) { // check if head is null
            return null;
        }
        else{
            Node head = new Node(treeNodes.get(0));
            Queue<Node> queue = new LinkedList<>();
            queue.add(head); // add head to queue
            int i=1;
            while (!queue.isEmpty() && i < treeNodes.size()) { // build tree using level order insertion
                Node node = queue.poll();
                if (i < treeNodes.size() && !"NULL".equalsIgnoreCase(treeNodes.get(i))) { // left child
                    node.setLeft(new Node(treeNodes.get(i)));
                    queue.add(node.getLeft());
                }
                i++;
                if (i < treeNodes.size() && !"NULL".equalsIgnoreCase(treeNodes.get(i))) { // right child
                    node.setRight(new Node(treeNodes.get(i)));
                    queue.add(node.getRight());
                }
                i++;
            }
            return head;
        }
    }

    //    Print tree in a structured format
    public static void printTree(Node node, String space, Boolean left, Boolean rightPresent){
        if (node == null) { // base case
            return;
        }
        if (Boolean.TRUE.equals(left)) { // check if its left child
            System.out.println(space + "|--" + node.getData()); // print left child with -- notation
        }
        else{
            System.out.println(space + "|__" + node.getData()); // print right child with __ notation
        }
        int letterCount = node.getData().length(); // get length of node data

        String buffer = " ".repeat(Math.max(0, letterCount / 2)); // create buffer for spacing for big node data

        // check if we need to add pipe for left child when right child is present (beatification)
        boolean addPipe = Boolean.TRUE.equals(left) && Boolean.TRUE.equals(rightPresent);

        if(node.getLeft()!=null) {
            if(addPipe) {
                printTree(node.getLeft(), space + "|  " + buffer, true, node.getRight()!=null);
            }
            else{
                printTree(node.getLeft(), space + "   " + buffer, true, node.getRight()!=null);
            }
        }
        if(node.getRight()!=null) {
            if(addPipe) {
                printTree(node.getRight(), space + "|  " + buffer, false, false);
            }
            else {
                printTree(node.getRight(), space + "   " + buffer, false, false);
            }
        }
    }
}