#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

//Francisco 17178
//Guilherme 17182

//Lista
typedef struct sNo
{
    void* info;
    struct sNo* prox;
} No;
typedef struct
{
    No* prim;
    char (*equals) (void*, void*);
} Lista;
void inserirComeco(Lista* lista, void* info);
void inserirFinal(Lista* lista, void* info);
void inserirEm(Lista* lista, void* info, int onde);
void removerComeco(Lista* lista);
void removerFinal(Lista* lista);
void removerEm(Lista* lista, int onde);
int ondeEsta (Lista* lista, void* info);
void* getElemento(Lista* lista, int n);
void printLista(Lista* lista);
char equalsStr (char* str1, char* str2);

double** arquivoSist(char* nomeArquivo, int* ordem, Lista* variaveis);
char* textoArquivo(char* nomeArquivo, int* ordem);
void zerar(char** str1, char** str2);
double* resolverSistema(double** matriz, int ordem, Lista* variaveis);
char escalonar(double** matriz, int ordem, Lista* variaveis);


int main()
{
    printf("Escreva o diretorio do arquivo: ");
    char* nomeArq = (char*)malloc(sizeof(char)*100);
    scanf("%s", nomeArq);

    printf("\n");

    int ordem;
    Lista* variaveis = (Lista*)malloc(sizeof(Lista));
    (*variaveis).prim = NULL;
    (*variaveis).equals = (char(*)(void*,void*))&equalsStr;
    double** matriz = arquivoSist(nomeArq, &ordem, variaveis);

    if (!matriz)
    {
        printf("O numero de variaveis no arquivo nao corresponde ao numero de variaveis escrito no decorrer do arquivo!");
        return -1;
    }

    double* solucao = resolverSistema(matriz, ordem, variaveis);

    if (!solucao)
    {
        printf("Nao eh possivel encontrar a resposta do sistema");
        return -2;
    }

    //Mostrar a solucao
    printf("\nSolucao encontrada:\n");
    int i;
    for (i = 0; i<ordem; i++)
        printf("  %s = %lf\n", (char*)getElemento(variaveis, i), *(solucao+i));

    printf("\nDigite qualquer coisa para finalizar o programa:\n");
    char* a; //Para ,se a pessoa rodar direto no .exe, poder ver o resultado do sistema
    scanf("%s", &a);
    return 0;
}

//Metodos para mexer com o arquivo
double** arquivoSist(char* nomeArquivo, int* ordem, Lista* variaveis)
{
    char* strArq = textoArquivo(nomeArquivo, ordem);

    //Instanciar
    double** matriz = (double**)malloc(*ordem * sizeof(double*));
    int i;
    for(i = 0; i < *ordem; i++)
        *(matriz+i) = (double*)malloc((*ordem + 1) * sizeof(double));

    int j;
    //Deixa a matriz cheia de zeros
    for(i = 0; i < *ordem; i++)
        for(j = 0; j < (*ordem + 1); j++)
            *(*(matriz+i)+j) = 0;

    int auxSinal = 1;
    char* auxInt;
    char* auxStr;

    zerar(&auxInt, &auxStr);

    int qtdVariaveis = 0;

    int linha = 0;
    for(i = 0; ; i++)
    {
        double valor;
        if(*(strArq+i) == '-' || *(strArq+i) == '+' || *(strArq+i) == '=' || *(strArq+i) == '\n' || *(strArq+i) == '\0')
        {
            valor = (double)(((*auxInt=='\0')?1:atoi(auxInt))*auxSinal);
            if(*auxStr != '\0')
                if(ondeEsta(variaveis, auxStr) < 0)
                {
                    //Verifica a quantidade exata de veriaveis(se nao possui mais do que pode)
                    if (qtdVariaveis >= *ordem)
                        return NULL;
                    inserirFinal(variaveis, auxStr);
                    qtdVariaveis++;
                }

            if(*auxStr == '\0')
            {
                if(*(strArq+i) == '\n' || *(strArq+i) == '\0')
                    *(*(matriz+linha)+*ordem) += valor;
            }
            else
                *(*(matriz+linha)+ondeEsta(variaveis, auxStr)) += valor;

            auxSinal  = 1;

            zerar(&auxInt, &auxStr);
        }

        if(*(strArq+i) == '-' )
            auxSinal  = -1;

        if(*(strArq+i) == '\n')
        {
            linha++;
            //Verifica se nao ha mais equacoes do que variaveis
            if (linha >= *ordem)
                break;
        }

        if(*(strArq+i) == '\0')
            break;

        if(isalpha(*(strArq+i)))
        {
            int j;
            for(j = 0;;j++)
                if(*(auxStr+j) == '\0')
                {
                    *(auxStr+j) = *(strArq+i);
                    *(auxStr+(j + 1)) = '\0';
                    break;
                }
        }else
        if(isdigit(*(strArq+i)))
        {
            int j;
            for(j = 0;;j++)
                if(*(auxInt+j) == '\0')
                {
                    *(auxInt+j) = *(strArq+i);
                    *(auxInt+(j + 1)) = '\0';
                    break;
                }
        }
    }

    //Da free no que deu o malloc
    free(auxStr);
    free(auxInt);
    free(strArq);

    //Verifica se nao ha variaveis a menor do que deveria
    if (qtdVariaveis != *ordem)
        return NULL;
    return matriz;
}

char* textoArquivo(char* nomeArquivo, int* ordem)
{
    //Leitura do arquivo
    FILE* file = fopen(nomeArquivo, "r");
    char* auxRet = (char*)malloc(sizeof(char) * 7500);
    char* auxConcat = (char*)malloc(sizeof(char) * 1024);
    *auxRet = '\0';

    fscanf(file, "%i", ordem);
    fgets(auxConcat, 1024, file);
    while(fgets(auxConcat, 1024, file) != NULL)
        strcat(auxRet, auxConcat);

    //Cria vetor de char com tamanho aux + 1
    char* ret = (char*)malloc(sizeof(char)*(strlen(auxRet) + 1));
    strcpy(ret, auxRet);

    //Termina a leitura do arquivo
    free(auxConcat);
    free(auxRet);
    fclose(file);

    return ret;
}
//Fim dos metodos para mexer com o arquivo

//Metodos para resolver o sistema
double* resolverSistema(double** matriz, int ordem, Lista* variaveis)
{
    //Faz o escalonamento
    char res = escalonar(matriz, ordem, variaveis);
    if (!res)
        return NULL;

    //Coloca valores em int* e char***
    double* solucao = (double*)malloc(sizeof(double)*ordem);
    int i;
    for(i=0; i<ordem; i++)
        *(solucao+i) = *(*(matriz+i)+ordem);

    return solucao;
}

void zerar(char** str1, char** str2)
{
    *str1 = (char*)malloc(sizeof(char) * 255);
    **str1  = '\0';
    *str2 = (char*)malloc(sizeof(char) * 255);
    **str2  = '\0';
}

char escalonar(double** matriz, int ordem, Lista* variaveis)
{
    int i;
    for (i = 0; i<ordem; i++)
    {
        double elem = *(*(matriz+i)+i);
        if (elem == 0)
        {
            int numLinha = -1; //Se nao houver zero

            int j;
            for (j=i + 1; j<ordem; j++)
            //Percorre toda linha procurando um zero numa posicao especifica daquela linha
            if (*(*(matriz+j)+i) != 0)
                numLinha = j;

            if (numLinha < 0)
                return 0; //Nao e possivel resolver o sistema

            double* aux = *(matriz+i);
            *(matriz+i) = *(matriz+numLinha);
            *(matriz+numLinha) = aux;

            elem = *(*(matriz+i)+i);
        }

        double multiplicador = 1/elem;
        int j;
        for(j=0; j<=ordem; j++) //Percorre todos os elementos da linha multiplicando pelo multiplicador
            *(*(matriz+i)+j) *= multiplicador;

        for (j=0; j<ordem; j++)
        if (j != i)
        {
            double multiplo = - *(*(matriz+j)+i);

            //Multiplica a linha escolhida por multiplicador e somar com a linha j
            int k;
            for(k=0; k<=ordem; k++)
                *(*(matriz+j)+k) += multiplo * *(*(matriz+i)+k);
            //Percorre todos os elementos da linha j somando com a multiplicacao do elemento correspondende da linha i por multiplo

            *(*(matriz+j)+i) = 0;
        }

    }

    return 1;//Nao deu erro
}
//Fim dos metodos para resolver o sistema

//Metodos da Lista Ligada

//Metodos Inserir
void inserirComeco(Lista* lista, void* info)
{
    No* novoPrim = (No*)malloc(sizeof(No));
    (*novoPrim).info = info;
    (*novoPrim).prox = (*lista).prim;

    (*lista).prim = novoPrim;
}

void inserirFinal(Lista* lista, void* info)
{
    if ((*lista).prim == NULL)
    {
        inserirComeco(lista, info);
        return;
    }

    No* atual = (*lista).prim;
    while ((*atual).prox != NULL)
        atual = (*atual).prox;

    No* novo = (No*)malloc(sizeof(No));
    (*novo).info = info;
    (*novo).prox = NULL;

    (*atual).prox = novo;
}

void inserirEm(Lista* lista, void* info, int onde)
{
    if (onde == 0)
    {
        inserirComeco(lista, info);
        return;
    }

    No* atual = (*lista).prim;
    int i;
    for(i=1; i<onde; i++)
    {
        if (atual == NULL)
            return;
        atual = (*atual).prox;
    }

    //O No deve ser adicionado no atual.prox
    No* novo= (No*)malloc(sizeof(No));
    (*novo).info = info;
    (*novo).prox = (*atual).prox;

    (*atual).prox = novo;
}
//Fim dos metodos inserir

//Metodos remover
void removerComeco(Lista* lista)
{
    if ((*lista).prim == NULL)
        return;

    No* aux = (*lista).prim;
    (*lista).prim = (*(*lista).prim).prox;
    free(aux);
}

void removerFinal(Lista* lista)
{
    if ((*lista).prim == NULL)
        return;

    No* anterior = NULL;
    No* atual = (*lista).prim;
    while ((*atual).prox != NULL)
    {
        anterior = atual;
        atual = (*atual).prox;
    }

    if (anterior == NULL) //So tinha um elemento
        (*lista).prim = NULL;
    else
        (*anterior).prox = NULL;

    //Libera o espaco do ultimo No
    free(atual);
}

void removerEm(Lista* lista, int onde)
{
    if (onde == 0)
    {
        removerComeco(lista);
        return;
    }

    No* atual = (*lista).prim;
    int i;
    for(i=1; i<onde; i++)
    {
        if (atual == NULL)
            return;
        atual = (*atual).prox;
    }

    //atual.prox deve ser removido
    No* aux = (*atual).prox;
    if (aux == NULL)
        return;
    (*atual).prox = (*(*atual).prox).prox;

    //libera o espaco do noh removido
    free(aux);
}
//Fim dos metodos remover

//Metodos Restantes
int ondeEsta (Lista* lista, void* info)
{
    No* atual = (*lista).prim;
    int i = 0;
    for(; atual != NULL; i++, atual = (*atual).prox)
        if( (*(*lista).equals)((*atual).info, info) )
            return i;

    return -1;
}

void* getElemento(Lista* lista, int n)
{
    No* atual = (*lista).prim;
    int i;
    for (i=0; atual != NULL; i++, atual = (*atual).prox)
        if (i == n)
            return (*atual).info;
    return NULL;
}

void printListaStr(Lista* lista)
{
    No* atual = (*lista).prim;
    while(atual != NULL)
    {
        printf("%s -> ", (*atual).info);
        atual = (*atual).prox;
    }
    printf("null");
}

char equalsStr (char* str1, char* str2)
{
    return strcmp(str1, str2) == 0;
}
//Fim dos metodos restantes
//Fim dos metodos da Lista Ligada
