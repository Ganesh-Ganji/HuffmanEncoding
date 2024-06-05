# HuffmanEncoding
import java.util.*;

public class EncodingPart {
    HashMap<Character,String> encode;
    private class Node{
        char data;
        int cost;
        Node left,right;
        Node(char data,int cost){
            this.data=data;
            this.cost=cost;
        }

    }
    private String encryption;
    EncodingPart(){
        Random rand=new Random();
        int[] cnt=new int[93];
        for(int i=0;i<cnt.length;i++){
            cnt[i]=rand.nextInt(5)+1;
        }

        StringBuilder temp=new StringBuilder();
        for(int i=0;i<cnt.length;i++){
            for(int j=0;j<cnt[i];j++) {
                temp.append((char) (i + 33));
            }
        }
        encryption=temp+"";

        //building tree for encoding

        PriorityQueue<Node> minHeap=new PriorityQueue<>((x,y)-> x.cost-y.cost);
        for(int i=0;i<cnt.length;i++){
            if(cnt[i]!=0){
                minHeap.add(new Node((char)(i+33),cnt[i]));
            }
        }

        while(minHeap.size()>1){

            Node first=minHeap.remove();
            Node second=minHeap.remove();
            Node newNode=new Node('\0',first.cost+ second.cost);
            newNode.left=first;
            newNode.right=second;
            minHeap.add(newNode);

        }
        Node root=minHeap.remove();
        encode=new HashMap<>();
        initEncoder(root,"");
    }
    public void initEncoder(Node root,String s){
        if(root==null){
            return;
        }
        if(root.left==null && root.right==null){
            encode.put(root.data,s);
            return;
        }
        initEncoder(root.left,s+"0");
        initEncoder(root.right,s+"1");
    }

    public String encodeIt(String msg){
        StringBuilder res=new StringBuilder();
        for(int i=0;i<msg.length();i++){
            res.append(encode.get(msg.charAt(i)));
        }
        return res+"%"+encryption;
    }
}
