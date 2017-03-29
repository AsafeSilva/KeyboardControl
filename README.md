# KeyboardControl
Controle o teclado do PC com seu Arduino (Uno, MEGA, NANO...)

## Software PC

Nesta primeira fase do projeto, o programa que "roda" no PC está dependente do [Processing](https://processing.org/download/) (Trabalhei com o Processing, pois estava tendo alguns problemas com o NetBeans e Eclipse). Em breve será um executável independente de outro programa.

Para executar o programa, basta abrir o executável 'KeyboardControl', que está na pasta application.windowsXX (escolha a pasta de acordo com seu sistema operacional. 64 ou 32 bits).

O Arduino, se comunicará com o PC, através da porta USB.

O uso do programa é bastante simples!
Primeiro, clique no botão de procurar as portas 'COM'. Depois selecione na lista, a porta que seu arduino está conectado.
Se escolheu a porta correta, seu Arduino será devidamente conectado!

O botão 'READ DATA', tem a função de habilitar e desabilitar o controle do teclado através do Arduino.

![Screenshot] x.png

## Biblioteca Arduino

A biblioteca é bem simples de ser utilizada!

Antes de tudo, instale a [bilioteca](https://github.com/AsafeSilva/KeyboardControl/tree/master/Arduino) na IDE do Arduino (Se não souber como faz da uma *googlada*), ou copie os arquivos da pasta ['Arduino/src'](https://github.com/AsafeSilva/KeyboardControl/tree/master/Arduino/src) na pasta do seu projeto.

Feito isso, você estará pronto pra programar!

Primeiramente inclua a biblioteca:

```ruby
#include "KeyboardControl.h"
```

A biblioteca é composta por apenas 3 métodos além dos construtores.

