package com.roger.projectadam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;

public class Tratamento_Mensagens {
    public static String mensagem;
    public static String resposta;
    public static String msgNormalizada;
    public static String[] res;
    
    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }
    
    public void tratarMensagem(){
        String msg = mensagem.toLowerCase();
        msgNormalizada = Normalizer.normalize(msg, Normalizer.Form.NFD);
        msgNormalizada = msgNormalizada.replaceAll("[ ?,.!*#$%()+=ªº]","");
        msgNormalizada = msgNormalizada.replaceAll("[^\\p{ASCII}]", "");
        System.out.println(msgNormalizada);
    }
    
    public void lerArquivoRespostas() throws FileNotFoundException, IOException{
        File arq = new File("respostas.txt");
        String arqDir = arq.getAbsolutePath();
        BufferedReader buffRead = new BufferedReader(new FileReader(arqDir));
        int vetSize = 0;
        while(buffRead.readLine() != null){
            vetSize += 1;
        }
        buffRead.close();

        BufferedReader br = new BufferedReader(new FileReader(arqDir));

        res = new String[vetSize];

        for(int i = 0; i < vetSize; i++){
            res[i] = br.readLine();
        }
        br.close();
    }
    
    public String buscarResposta() throws IOException{
        String respostaFinal = "";
        for(int i = 0; i < res.length; i++){
            String[] prg_res = res[i].split("\\*");
            String msgConf = prg_res[0].replaceAll(" ","");
            if(msgConf.equals(msgNormalizada)){
                respostaFinal = prg_res[1];
            }
        }
        return respostaFinal;
    }
}
