package org.joker;

import java.util.*;

public class Main {
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
            System.out.println("No data entered");
        }
        else{
            tree = tree.replaceAll("\\s{2,}", " "); // remove all extra space
            List<String> treeNodes = new ArrayList<>(List.of(tree.split(" ")));

            if(treeNodes.isEmpty()){
                System.out.println("No data entered");
            }
            else{
                Node head = buildTree(treeNodes);
                System.out.println("  ");
                System.out.println("Tree is built");
                System.out.println(" ");
                assert head != null;
                printTree(head,"", true, false);
                System.out.println(" ");
                System.out.println("Tree is printed");
            }
        }
    }

    public static Node buildTree(List<String> treeNodes){
        if("NULL".equalsIgnoreCase(treeNodes.get(0))){
            return null;
        }
        else{
            Node head = new Node(treeNodes.get(0));
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            int i=1;
            while(!queue.isEmpty() && i< treeNodes.size()){
                Node node = queue.poll();
                if(i< treeNodes.size() && !"NULL".equalsIgnoreCase(treeNodes.get(i))){
                    node.setLeft(new Node(treeNodes.get(i)));
                    queue.add(node.getLeft());
                }
                i++;
                if(i< treeNodes.size() && !"NULL".equalsIgnoreCase(treeNodes.get(i))){
                    node.setRight(new Node(treeNodes.get(i)));
                    queue.add(node.getRight());
                }
                i++;
            }
            return head;
        }
    }

    public static void printTree(Node node, String space, Boolean left, Boolean rightPresent){
        if(node==null){
            return;
        }
        if(Boolean.TRUE.equals(left)) {
            System.out.println(space + "|--" + node.getData());
        }
        else{
            System.out.println(space + "|__" + node.getData());
        }
        int letterCount = node.getData().length();

        String buffer = " ".repeat(Math.max(0, letterCount / 2));

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