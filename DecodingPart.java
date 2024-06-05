import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class DecodingPart {
    HashMap<String,Character> decode;
    private class Node{
        char data;
        int cost;
        Node left,right;
        Node(char data,int cost){
            this.data=data;
            this.cost=cost;
        }

    }

    DecodingPart(String encryption){
        int[] cnt=new int[93];
        for(int i=0;i<encryption.length();i++){
            cnt[encryption.charAt(i)-33]++;
        }

        PriorityQueue<Node> minHeap=new PriorityQueue<>((x, y)-> x.cost-y.cost);
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
        decode=new HashMap<>();
        initDecoder(root,"");



    }

    public void initDecoder(Node root, String s){
        if(root==null){
            return;
        }
        if(root.left==null && root.right==null){
            decode.put(s,root.data);
            return;
        }
        initDecoder(root.left,s+"0");
        initDecoder(root.right,s+"1");
    }

    public String decodeIt(String msg){
        StringBuilder res=new StringBuilder();
        StringBuilder key=new StringBuilder();
        for(int i=0;i<msg.length();i++){
            key.append(msg.charAt(i));
            if(decode.containsKey(key+"")){
                res.append(decode.get(key+""));
                key=new StringBuilder();
            }
        }

        return res+"";
    }

}
