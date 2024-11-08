//Código Malha Aberta

#include <TimerOne.h>

volatile const int PinPWM = 9;
volatile float dutyCycle = 0.0;
volatile int sensorValue = 0;
volatile float voltage = 0.0;

void setup() {
  Timer1.initialize(10000);  // Define o período da interrupção
  Timer1.attachInterrupt(funcao_interrupcao); // Liga a função de controle na interrupção
  Serial.begin(115200); // Inicia a comunicação serial
}

void loop() {
  // Verifica se há algum dado disponível na Serial para atualizar a referência
  if (Serial.available() > 0) {
    dutyCycle = Serial.parseFloat(); // Lê o valor da referência enviado pela Serial
    while (Serial.available() > 0) {
      Serial.read(); // Limpa o buffer serial
    }
  }
    // Aplica o valor de dutyCycle no PWM
  Timer1.pwm(PinPWM, (dutyCycle / 100.0) * 1023); 
}

void funcao_interrupcao() {
  sensorValue = analogRead(A0); // Lê o valor analógico
  voltage = sensorValue * (5.0 / 1023.0); // Converte a leitura para tensão (0-5V)

  // Exibe os valores para monitoramento
  Serial.print(5.0*dutyCycle/100.0, 4);
  Serial.print("   ");
  Serial.println(voltage, 4);
}