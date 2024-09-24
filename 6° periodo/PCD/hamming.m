% Funcao Principal

function saida = hamming(k,n)
 paridade_indice = k - n;
 identidade = eye(n);
 saida = identidade;
 
 for i = 1:k
     teste = i;
     if (teste == 1 || EhPotencia(teste,2))
         continue;
     end
     
     trans = dec2bin(i);
     display(trans);
 end
 
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
