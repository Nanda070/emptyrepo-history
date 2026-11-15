#include <stdio.h>
#include "parity.h"

int main() {

    ParityArray arr;
    InitParityArray(&arr,5);

    InsertToParityArray(&arr,2);
    InsertToParityArray(&arr,7);
    InsertToParityArray(&arr,1);
    // здесь следует проверить успешно ли резервирование, но какова задача
    // я полагаю, он не просил меня писать вводную часть
    // это пишет тот, кто пишет вводные данные

    PrintParityArray(&arr);

    InsertToParityArray(&arr,9);

    printf("\n");

    InsertToParityArray(&arr,4);

    PrintParityArray(&arr);

    DisposeParityArray(&arr);

    return 0;
}
