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
function saida = hammingVerificacao(matriz)
     [linhas, colunas] = size(matriz);
     matriz_paridade = matriz(1:linhas, (linhas+1):colunas);
     transposta_paridade = transpose(matriz_paridade);
     identidade = eye(colunas-linhas);
     saida = [transposta_paridade, identidade];
end
% ------------------------- Distância mínima do codificador ------------------------
function saida = distMinima(matriz)
     vetor = [];
     [linhas, colunas] = size(matriz);

     for (i = 1:linhas)
         linha_atual = matriz(i, :);
         peso = sum(linha_atual(:));
         vetor = [vetor, [peso]];
     end

     saida = min(vetor(:));
end
% ------------------------- Detecção e Correção de Erros ---------------------------
function saida = detecErros(matriz)
     dist = distMinima(matriz);
     saida = floor(dist-1);
end

function saida = correcErros(matriz)
     dist = distMinima(matriz);
     saida = floor((dist-1)/2);
end
% ------------------------------ Mensagens Possíveis -------------------------------
function saida = mensagens(n)
     saida = [];
     quantidade = (2^n)-1;
     
     for (i = 0:quantidade)
         saida = [saida, i];
     end

     saida = dec2bin(saida, 3);
end
% ------------------------------ Códigos equivalentes ------------------------------
function saida = codigos(n)
     saida = [];
     
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
