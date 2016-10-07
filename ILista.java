package grafos.util;


public interface ILista {

    public boolean estaVazia();

    public int obterTamanho();

    public void inserirInicio(Object o);

    public void inserirFinal(Object o);

    public void inserir(Object o, int index);

    public Object removerInicio();

    public Object removerFinal();

    public Object remover(int index);

    public Object recuperar(int index);

    public Iterador iterador();
}
