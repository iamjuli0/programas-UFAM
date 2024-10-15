## Copyright (C) 2024 julio
##
## This program is free software: you can redistribute it and/or modify
## it under the terms of the GNU General Public License as published by
## the Free Software Foundation, either version 3 of the License, or
## (at your option) any later version.
##
## This program is distributed in the hope that it will be useful,
## but WITHOUT ANY WARRANTY; without even the implied warranty of
## MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
## GNU General Public License for more details.
##
## You should have received a copy of the GNU General Public License
## along with this program.  If not, see <https://www.gnu.org/licenses/>.

## -*- texinfo -*-
## @deftypefn {} {@var{retval} =} hammingGeradora (@var{input1}, @var{input2})
##
## @seealso{}
## @end deftypefn

## Author: julio <julio@JULIOS-COMPUTAD>
## Created: 2024-09-28

% >>>>>>>>>>>>>>>>>>>>>>>>>>> Funcoes Principais <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

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

% >>>>>>>>>>>>>>>>>>>>>>>>>>> Funcoes Secundarias <<<<<<<<<<<<<<<<<<<<<<<<<<<<<

function resultado = EhPotencia(k,pot)
if k > 0 && pot > 1
     log_result = log(k) / log(pot);  % calcula o logaritmo na base desejada
     resultado = (floor(log_result) == log_result);  % verifica se é um inteiro

     else
         resultado = false;  % é inválido
     end
end

function quantidade = bitsParidade(k)
     quantidade = 0;
     for (i = 1:k)
         if EhPotencia(i,2)
             quantidade++;
         end
     end
end

function trans = transformada(k, paridade)
     trans = dec2bin(k);
     trans = str2num(trans);
     trans = trans/(10^paridade);
     trans = floor(trans);
     trans = mod(trans,10);
end
