//Código para Controle em Malha Fechada - Malha Direta
#include <TimerOne.h>

volatile const int PinPWM = 9;
volatile float dutyCycle = 0.0;
volatile int sensorValue = 0;
volatile float voltage = 0.0;
volatile float Ref = 0.0;
volatile float u[3] = {0.0000, 0.0000, 0.0000};
volatile float e[3] = {0.0000, 0.0000, 0.0000};
volatile float R[3] = {39.642289513645814, -73.560948726344961,  34.420823343254852};
volatile float S[3] = {1.0000, -1.0000,0.0000};

void setup() {
  Timer1.initialize(10000);  // Define o período da interrupção
  Timer1.attachInterrupt(funcao_interrupcao); // Liga a função de controle na interrupção
  Serial.begin(115200); // Inicia a comunicação serial

}

void loop() {
  // Verifica se há algum dado disponível na Serial para atualizar a referência

  if (Serial.available() > 0) {
    Ref = Serial.parseFloat(); // Lê o valor da referência enviado pela Serial
    while (Serial.available() > 0) {
    Serial.read(); // Limpa o buffer serial
    }
  }
   Timer1.pwm(PinPWM, (int)((dutyCycle / 100.0) * 1023.0));
}

void funcao_interrupcao() {
  sensorValue = analogRead(A0); // Lê o valor analógico
  voltage = sensorValue * (5.0 / 1023.0); // Converte a leitura para tensão (0-5V)

 if(Ref>0.0){
  dutyCycle = controle_RS(Ref, voltage); // Calcula o controle baseado na referência e tensão atual
  }
  else{dutyCycle = 0.0;}
   // Aplica o valor de dutyCycle no PWM
 
// Exibe os valores para monitoramento
Serial.print(Ref,4);
Serial.print("   ");
Serial.print((dutyCycle/100.0), 4);
Serial.print("   ");
Serial.println(voltage, 4);
}

// Função de controle RST
float controle_RS(volatile float Ref, volatile float voltage) {
  // Calcula o erro
  e[0] = Ref - voltage;

  // Calcula o sinal de controle u[0]
  u[0] = -(S[1] * u[1] + S[2] * u[2]) + (R[0] * e[0] + R[1] * e[1] + R[2] * e[2]);

  // Limita o valor de u[0] entre 0 e 100 (para duty cycle)
  if (u[0] >= 100.0) { u[0] = 100.0;}
  if (u[0] <= 0.0) { u[0] = 0.0; }

  // Atualiza os estados anteriores de u e e
  u[2] = u[1];
  u[1] = u[0];
  e[2] = e[1];
  e[1] = e[0];

  return u[0]; // Retorna o valor de controle atual
}