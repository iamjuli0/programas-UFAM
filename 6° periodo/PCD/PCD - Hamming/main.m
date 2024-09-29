% Atividade - Codificação de Hamming
% A atividade deverá ser feita no Matlab ou Octave, e deverá ser entregue no dia 30/09/2024. A atividade deverá ser feita da seguinte forma:
% 1. Montar a matriz geradora e a matriz de verificação de paridade do codificador de Hamming deacordo com os valores que consta no ANEXO I;
% 2. Montar a tabela de palavra- código do codificador, a partir da matriz geradora obtida no item1;
% 3. Calcular a distância mínima do codificador;
% 4. Quantos erros o codificador detecta e corrige?

clear; clc;

display("ANEXO I");
display("--------------------------------------------------");
display("a. Código (7,4)");

mGeradora = hammingGeradora(7,4);
mverificaH = hammingVerificacao(mGeradora);
distancia1 = distMinima(mGeradora);
[d1, c1] = analisaErros(mGeradora);

fprintf("Matriz Geradora - Código (7,4)\n");
disp(mGeradora);
fprintf("\n");

fprintf("Matriz Verificacao de Paridade - Código (7,4)\n");
disp(mverificaH);
fprintf("\n");

fprintf("Distância Mínima: %d\n", distancia1);
fprintf("\nErros: \nDetecção: %d\nCorreção: %d\n", d1, c1);

mensagensBinarias1 = mensagensImpressao(4);
codigosBinarios1 = codigosImpressao(mensagensBinarias1, mGeradora);
dadosTabela1 = [cellstr(mensagensBinarias1), cellstr(codigosBinarios1)];

display("--------------------------------------------------");
display("b. Código (15,11)");

mGeradora = hammingGeradora(15,11);
mverificaH = hammingVerificacao(mGeradora);
distancia2 = distMinima(mGeradora);
[d2, c2] = analisaErros(mGeradora);

fprintf("Matriz Geradora - Código (15,11)\n");
disp(mGeradora);
fprintf("\n");

fprintf("Matriz Verificacao de Paridade - Código (15,11)\n");
disp(mverificaH);
fprintf("\n");

fprintf("Distância Mínima: %d\n", distancia2);
fprintf("\nErros: \nDetecção: %d\nCorreção: %d\n", d2, c2);

mensagensBinarias2 = mensagensImpressao(11);
codigosBinarios2 = codigosImpressao(mensagensBinarias2, mGeradora);
dadosTabela2 = [cellstr(mensagensBinarias2), cellstr(codigosBinarios2)];

display("--------------------------------------------------");
display("c. Código (3,1)");

mGeradora = hammingGeradora(3,1);
mverificaH = hammingVerificacao(mGeradora);
distancia3 = distMinima(mGeradora);
[d3, c3] = analisaErros(mGeradora);

fprintf("Matriz Geradora - Código (3,1)\n");
disp(mGeradora);
fprintf("\n");

fprintf("Matriz Verificacao de Paridade - Código (3,1)\n");
disp(mverificaH);
fprintf("\n");

fprintf("Distância Mínima: %d\n", distancia3);
fprintf("\nErros: \nDetecção: %d\nCorreção: %d\n", d3, c3);

mensagensBinarias3 = mensagensImpressao(1);
codigosBinarios3 = codigosImpressao(mensagensBinarias3, mGeradora);
dadosTabela3 = [cellstr(mensagensBinarias3), cellstr(codigosBinarios3)];

display("--------------------------------------------------");

f = figure('Position', [100, 100, 400, 400]);
tabela1 = uitable(f, 'Data', dadosTabela1, 'ColumnName', {'Mensagens', 'Códigos'}, 'Position', [100 200 360 350]);
set(tabela1, 'ColumnWidth', {100, 100, 100});

tabela2 = uitable(f, 'Data', dadosTabela2, 'ColumnName', {'Mensagens', 'Códigos'}, 'Position', [500 200 360 350]);
set(tabela2, 'ColumnWidth', {100, 150, 100});

tabela3 = uitable(f, 'Data', dadosTabela3, 'ColumnName', {'Mensagens', 'Códigos'}, 'Position', [900 200 360 350]);
set(tabela1, 'ColumnWidth', {100, 100, 100});

