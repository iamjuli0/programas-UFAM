% -----------------------
% ATUALIZACAO 26/09/2024
% -----------------------

% >>>>>>>>>>>>>>>>>>>>>>>>>>> Funcoes Principais <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
% ---------------------------- Geradora de Hamming ---------------------------------
function saida = hammingGeradora(k,n)
 indice_paridade = bitsParidade(k) - 1;
 identidade = eye(n);
 matriz_paridade = [];

 for (i = 1:k)
     teste = i;
     if (teste == 1 || EhPotencia(teste,2))
         continue;
     end

     coluna_atual = [];
     for (j = 0:indice_paridade)
         elemento = transformada(i, j);
         coluna_atual = [coluna_atual; elemento];
     end
     
     matriz_paridade = [matriz_paridade, coluna_atual];
 end
 
 saida = [identidade, transpose(matriz_paridade)];
end
% ---------------------------- Verificação de Paridade Hamming ---------------------
function saida = hammingVerificacao(matrizGeradora)
     [linhas, colunas] = size(matrizGeradora);
     matriz_paridade = matrizGeradora(1:linhas, (linhas+1):colunas);
     transposta_paridade = transpose(matriz_paridade);
     identidade = eye(colunas-linhas);
     saida = [transposta_paridade, identidade];
end
% ------------------------- Distância mínima do codificador ------------------------
function saida = distMinima(matrizGeradora)
     vetor = [];
     [linhas, colunas] = size(matrizGeradora);

     for (i = 1:linhas)
         linha_atual = matrizGeradora(i, :);
         peso = sum(linha_atual(:));
         vetor = [vetor, [peso]];
     end

     saida = min(vetor(:));
end
% ------------------------- Detecção e Correção de Erros ---------------------------
function [d, c] = analisaErros(matrizGeradora)
     dist = distMinima(matrizGeradora);
     d = floor(dist-1);
     c = floor((dist-1)/2);
end
% ------------------------------ Mensagens Possíveis -------------------------------
function saida = mensagensImpressao(entrada)
     saida = [];
     quantidade = (2^entrada)-1;

     for (i = 0:quantidade)
         saida = [saida, i];
     end
     saida = dec2bin(saida, entrada);
end
% ------------------------------ Códigos Possíveis ---------------------------------
function saida = codigosImpressao (mensagens, mGeradora)
  [linhas, colunas] = size(mensagens);
  saida = [];

  for (i = 1:linhas)
    temp = [];

    for (j = 1:colunas)
      num = str2num(mensagens(i,j));
      linhaAtual = mGeradora(j,:);
      linhaMultiplicada = num*linhaAtual;
      temp = [temp; linhaMultiplicada];
      somas = sum(temp);
      res = mod(somas, 2);
    end
    saida = [saida; res];

  end
  saida = num2str(saida);
  saida = strrep(saida, ' ', '');
end
% >>>>>>>>>>>>>>>>>>>>>>>>>>> Funcoes Secundarias <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
% ----------------- Verifica se um número é potencia de outro ----------------------
function resultado = EhPotencia(k,pot)
if k > 0 && pot > 1
     log_result = log(k) / log(pot);  % calcula o logaritmo na base desejada
     resultado = (floor(log_result) == log_result);  % verifica se é um inteiro
     
     else
         resultado = false;  % é inválido
     end
end
% ------ Verifica a quantidade de bits de paridade de um código com saída k --------
function quantidade = bitsParidade(k)
     quantidade = 0;
     for (i = 1:k)
         if EhPotencia(i,2)
             quantidade++;
         end
     end
end
% ---- Verifica se tal valor k tem o bits significativos em função da paridade -----
function trans = transformada(k, paridade)
     trans = dec2bin(k);
     trans = str2num(trans);
     trans = trans/(10^paridade);
     trans = floor(trans);
     trans = mod(trans,10);
end
