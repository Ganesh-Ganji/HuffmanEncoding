public class Main {
    public static void main(String[] args) {
        EncodingPart encoder=new EncodingPart();

        String str="hello ,what are you doing ?";
        str=str.replace(" ","-");
        String encryptedMsg=encoder.encodeIt(str);
        StringBuilder val=new StringBuilder();
        String key="";
        for(int i=0;i<encryptedMsg.length();i++){
            if(encryptedMsg.charAt(i)=='%'){
                key=encryptedMsg.substring(i+1);
                break;
            }
            val.append(encryptedMsg.charAt(i));
        }

        DecodingPart decoder=new DecodingPart(key);
        String message=decoder.decodeIt(val+"");
        message=message.replace("-"," ");
        System.out.println(message);

    }
}
