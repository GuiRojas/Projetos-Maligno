#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
//1 => arquivo n�o encontrado
int main(){
    FILE *arq;
    char dir[1000];
    char linha[100000];
    char ch;
    int qtd;

    printf("Digite o caminho do Arquivo com o sistema:");
    scanf("%s",dir);

    if((arq = fopen(dir,"r")) == NULL){
        printf("Erro! Arquivo n�o existe!");
        exit(1);
    }
    //l� a quantidade de express�es do sistema
    fscanf(arq,"%[^\n]",linha);
    printf("Acheeeiiiii %s",linha);
    qtd = atoi(linha);

    //l� o sistema char por char
    while((ch = fgetc(arq)) != EOF){
        int len = strlen(linha);
        linha[len] = ch;
        linha[len+1] = '\0';
        printf("%c", ch);
    }

    fclose(arq);

    printf("\n%s",linha);

    return 0;
}
