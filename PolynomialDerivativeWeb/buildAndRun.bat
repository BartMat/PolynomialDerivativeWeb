@echo off
call mvn clean package
call docker build -t pl.polsl/PolynomialDerivativeWeb .
call docker rm -f PolynomialDerivativeWeb
call docker run -d -p 9080:9080 -p 9443:9443 --name PolynomialDerivativeWeb pl.polsl/PolynomialDerivativeWeb