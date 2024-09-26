% -----------------------------
% ATUALIZACAO 26/09/2024
% -----------------------------

% Funcao Principal

function saida = hamming(k,n)
 indice_paridade = bits_paridade(k) - 1;
 identidade = eye(n);
 matriz_paridade = [];

 for i = 1:k
     teste = i;
     if (teste == 1 || EhPotencia(teste,2))
         continue;
     end

     coluna_atual = [];
     for j = 0:indice_paridade
         elemento = transformada(i, j);
         coluna_atual = [coluna_atual; elemento];
     end
     
     matriz_paridade = [matriz_paridade, coluna_atual];
 end
 
 saida = transpose(matriz_paridade);
end

% Funcoes Secundarias

function resultado = EhPotencia(k,pot)

 if k > 0 && pot > 1
     log_result = log(k) / log(pot);  % calcula o logaritmo na base desejada
     resultado = (floor(log_result) == log_result);  % verifica se é um inteiro
     else
         resultado = false;  % é inválido
    end

end

function quantidade = bits_paridade(k)

     quantidade = 0;
     for i = 1:k
         if EhPotencia(i,2)
             quantidade++;
         end
     end
end

function trans = transformada(valor, paridade)
     trans = dec2bin(valor);
     trans = str2num(trans);
     trans = trans/(10^paridade);
     trans = floor(trans);
     trans = mod(trans,10);
end
