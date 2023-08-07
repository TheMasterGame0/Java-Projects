/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projeto1;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Gabriel Henrique
 */
interface Classificavel{
    public boolean menorElemento(Classificavel obj);
}

class Classificador{
    
    public void ordena(ArrayList<Classificavel> a){
        Classificavel elem, menor;
        int pos;
        
        for(int i = 0; i < a.size() -1; i++){
            elem = a.get(i);
            menor = a.get(i+1);
            pos = i+1;
            
            for(int j=i+2; j < a.size(); j++){
                if(a.get(j).menorElemento(menor)){
                    menor = a.get(j);
                    pos = j;
                }
            }
            
            if(menor.menorElemento(elem)){
                a.set(i, a.get(pos));
                a.set(pos,elem);
            }
        }
    }
}

class Produto implements Classificavel{
    private int codigo;
    private String nome;
    private double preco;
    
    public Produto(int codigo, String nome, double preco){
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }
    
    @Override
    public boolean menorElemento(Classificavel obj){
        Produto compara = (Produto) obj;
        return this.codigo<compara.codigo;
    }
    public void print(){
        System.out.printf("%d %s %.2f\n",this.codigo, this.nome, this.preco);
    }
}

class Cliente implements Classificavel{
    private String cpf, nome, endereco;
    
    public Cliente(String cpf, String nome, String endereco){
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }
    
    @Override
    public boolean menorElemento(Classificavel obj){
        Cliente compara = (Cliente) obj;
        int comparacao = this.nome.compareTo(compara.nome);
        return comparacao < 0;
    }
    
    public void print(){
        System.out.println(this.cpf + " "+this.nome + " "+ this.endereco);
    }
}

class Servico implements Classificavel{
    private String data, tipoServico;
    private double preco;
    
    public Servico(String tipoServico, double preco, String data){
        this.preco = preco;
        this.data = data;
        this.tipoServico = tipoServico;
    }
    
    @Override
    public boolean menorElemento(Classificavel obj){
        Servico compara = (Servico) obj;
        return this.preco<compara.preco;
    }
    public void print(){
        System.out.printf("%s %.2f %s\n",this.tipoServico, this.preco, this.data);
    }
}




public class Projeto1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in); 
        int produtos = entrada.nextInt();
        ArrayList<Classificavel> p = new ArrayList<Classificavel>();
        int clientes = entrada.nextInt();
        ArrayList<Classificavel> c = new ArrayList<Classificavel>();
        int servicos = entrada.nextInt();
        ArrayList<Classificavel> s = new ArrayList<Classificavel>();
        entrada.nextLine();
        for(int i = 0; i < produtos; i++){
            Produto produto = new Produto(entrada.nextInt(), entrada.next(),entrada.nextFloat());
            p.add(produto);
        }
        entrada.nextLine();
        for(int i = 0; i < clientes; i++){
            Cliente cliente = new Cliente(entrada.next(), entrada.next(), entrada.next());
            c.add(cliente);
        }
        
        
        for(int i = 0; i < servicos; i++){
            Servico servico = new Servico(entrada.next(), entrada.nextDouble(), entrada.next());
            s.add(servico);
        }
        
        Classificador classificador = new Classificador();
        classificador.ordena(p);
        classificador.ordena(c);
        classificador.ordena(s);
        
        for(int i = 0; i < produtos; i++){
            ((Produto) p.get(i)).print();
        }
        for(int i = 0; i < clientes; i++){
            ((Cliente) c.get(i)).print();
        }
        for(int i = 0; i < servicos; i++){
            ((Servico) s.get(i)).print();
        }
    }
    
}
