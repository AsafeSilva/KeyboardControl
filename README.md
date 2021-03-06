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

Antes de tudo, [instale-a](https://github.com/AsafeSilva/KeyboardControl/tree/master/Arduino) na IDE do Arduino (Se não souber como faz, da uma *googlada*), ou copie os arquivos da pasta ['Arduino/src'](https://github.com/AsafeSilva/KeyboardControl/tree/master/Arduino/src) na pasta do seu projeto.

Feito isso, você estará pronto para programar!

Primeiramente inclua a biblioteca:

```
#include "KeyboardControl.h"
```

- A classe possui dois construtores:

Se a comunicação com o PC for através da porta USB da própria placa, ou seja, utilizando a comunicação Serial do próprio hardware (Pinos RX [0], TX [1]), use o construtor *default*:

```
KeyboardControl key;
```

Caso esteja usando a bilioteca SoftwareSerial, primeiro crie sua variável do tipo SoftwareSerial e passe-a como parâmetro no construtor:
[Note o '&']

```
#include <SoftwareSerial.h>

SoftwareSerial serial(7, 8); // RX, TX

KeyboardControl key(&serial);
```

Lembre-se de chamar no `void setup()` o método `begin()` com o *baudrate* de 9600:
```
Serial.begin(9600);
```


- A biblioteca possui 3 métodos:

```
// Pressionar tecla 
key.keyPress(int keycode);
```
```
// Soltar tecla
key.keyRelease(int keycode);
```
```
// Pressionar e soltar tecla
key.keyClick(int keycode);
```

Todos os três, recebem um parâmetro que é a tecla que é pressionada.
Para saber as teclas disponível, basta olhar o arquivo ['KeyCodes.h'](https://github.com/AsafeSilva/KeyboardControl/blob/master/Arduino/src/KeyCodes.h).
Esta bilioteca se comunica com o software no PC que utiliza a API *java.awt.Robot*. Onde sua clássica documentação pode ser encontrada [aqui](https://docs.oracle.com/javase/7/docs/api/java/awt/Robot.html). Todos os comandos foram retirados desta API, que estão listados [aqui](https://docs.oracle.com/javase/7/docs/api/constant-values.html#java.awt.event.KeyEvent.VK_0)! 
*Caso queira utilizar mais comandos que não estejam na `KeyCodes.h`, por favor, comunicar!*

Exemplos de uso da bilioteca podem ser visto na pasta ['Arduino/examples'](https://github.com/AsafeSilva/KeyboardControl/tree/master/Arduino/examples)
