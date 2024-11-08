//Código com realimentação de estados
#include TimerOne.h

volatile const int PinPWM = 9;
volatile float dutyCycle = 0.0;
volatile int sensorValue = 0;
volatile float voltage = 0.0;
volatile int sensorValue2 = 0;
volatile float voltage2 = 0.0;
volatile float Ref = 0.0;
volatile float u[3] = {0.0000, 0.0000, 0.0000};
volatile float e[3] = {0.0000, 0.0000, 0.0000};
volatile float R[3] = {39.642289513645814, -73.560948726344961,  34.420823343254852};
volatile float S[3] = {1.0000, -1.0000,0.0000};
volatile float ui[2] = {0.0,0.0};
volatile float K[3] = {59.4353,   37.4097,  19.1480};
volatile float K1[2] = {30.3391,   20.7473};
volatile float Ts = 10.0e-3;
void setup() {
  Timer1.initialize(10000);   Define o período da interrupção
  Timer1.attachInterrupt(funcao_interrupcao);  Liga a função de controle na interrupção
  Serial.begin(115200);  Inicia a comunicação serial

}

void loop() {
   Verifica se há algum dado disponível na Serial para atualizar a referência

  if (Serial.available()  0) {
    Ref = Serial.parseFloat();  Lê o valor da referência enviado pela Serial
    while (Serial.available()  0) {
    Serial.read();  Limpa o buffer serial
    }
  }
   Timer1.pwm(PinPWM, (int)((dutyCycle  100.0)  1023.0));
}

void funcao_interrupcao() {
  sensorValue = analogRead(A0);  Lê o valor digital d Capacitor 1
  voltage = sensorValue  (5.0  1023.0);  Converte a leitura para tensão (0-5V) do capacitor 1
  sensorValue2 = analogRead(A1);  Lê o valor digital do ponto V1
  voltage2 = sensorValue2  (5.0  1023.0)-voltage;  Converte a leitura para tensão (0-5V) do capacitor 2
 
 if(Ref0.0){
  dutyCycle = controle_RS(Ref, voltage);   Descomentando esta linha e comentando as duas abaixo você a aplicará o controle Dinâmico por Função de Transferência;
   dutyCycle = realimentacao_estados(Ref, voltage, voltage2);   Descomentando esta linha e comentando as outras duas(acima e abaixo) você aplicaráo controle realimentação de estados
dutyCycle = realimentacao_estados_integrador(Ref, voltage, voltage2);    Descomentando esta linha e comentando as outras duas acimas você insere a aplicação do controle integral com realimentação de estados
  }
  else{dutyCycle = 0.0;}
    Aplica o valor de dutyCycle no PWM
 
 Exibe os valores para monitoramento
Serial.print(Ref,4);
Serial.print(   );
Serial.print((dutyCycle100.0), 4);
Serial.print(   );
Serial.print(voltage, 4);
Serial.print(   );
Serial.println(voltage2, 4);
}

 Função de controle RST
float controle_RS(volatile float Ref, volatile float voltage) {
   Calcula o erro
  e[0] = Ref - voltage;

   Calcula o sinal de controle u[0]
  u[0] = -(S[1]  u[1] + S[2]  u[2]) + (R[0]  e[0] + R[1]  e[1] + R[2]  e[2]);

   Limita o valor de u[0] entre 0 e 100 (para duty cycle)
  if (u[0] = 100.0) { u[0] = 100.0;}
  if (u[0] = 0.0) { u[0] = 0.0; }

   Atualiza os estados anteriores de u e e
  u[2] = u[1];
  u[1] = u[0];
  e[2] = e[1];
  e[1] = e[0];

  return u[0];  Retorna o valor de controle atual
}



float realimentacao_estados(volatile float Ref, volatile float voltage, volatile float voltage2)
{
  
  e[0] = Ref - voltage;
  u[0] = K1[0]Ref-(K1[0]voltage + K1[1]voltage2);

   Limita o valor de u[0] entre 0 e 100 (para duty cycle)
  if (u[0] = 100.0) { u[0] = 100.0;}
  if (u[0] = 0.0) { u[0] = 0.0; }


  return u[0];  Retorna o valor de controle atual
  
  }

float realimentacao_estados_integrador(volatile float Ref, volatile float voltage, volatile float voltage2)
{

       Calcula o erro (Ref - saída)
    e[0] = Ref - voltage;

    Atualiza o estado do integrador (erro acumulado)
    ui[0] = ui[1] + Ts  e[0];
    ui[1] = ui[0];
  
  u[0] = -(K[0]  voltage + K[1]  voltage2) + K[2]  ui[0];

   Limita o valor de u[0] entre 0 e 100 (para duty cycle)
  if (u[0] = 100.0) { u[0] = 100.0;}
  if (u[0] = 0.0) { u[0] = 0.0; }


  return u[0];  Retorna o valor de controle atual
  
  }