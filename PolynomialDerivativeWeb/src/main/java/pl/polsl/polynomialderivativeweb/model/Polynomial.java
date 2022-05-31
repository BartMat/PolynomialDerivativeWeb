package pl.polsl.polynomialderivativeweb.model;

import java.util.ArrayList;
import javax.naming.spi.DirStateFactory;
import pl.polsl.polynomialderivativeweb.exceptions.NotEnoughDataInArray;

/**
 * Model(MVC pattern) representation of polynomial responsible for logic of the
 * application and updates
 *
 * @author Bartosz Matusik
 * @version 1.0
 */
public class Polynomial {

    /**
     * degree of polynomial
     */
    private int degree;
    /**
     * factors of polynomial
     */
    ArrayList<Double> factors;

    /**
     * Constructor with two parameters
     *
     * @param degree - degree of polynomial
     * @param factors - array of factors of polynomial
     */
    public Polynomial(int degree, ArrayList<Double> factors) {
        this.degree = degree;
        this.factors = new ArrayList<>(factors);

    }

    public Polynomial(Polynomial polynomial) {
        degree = polynomial.degree;
        factors = new ArrayList<>(polynomial.factors);
    }

    /**
     * Constructor with arbitrary number of parameters
     *
     * @param degree - degree of polynomial
     * @param factors - array of factors of polynomial
     */
    public Polynomial(int degree, Double... factors) {
        this.degree = degree;
        this.factors = new ArrayList<>();
        for (double i : factors) {
            this.factors.add(i);
        }
    }

    /**
     * Constructor with one parameter, all factors are zeroes
     *
     * @param degree - degree of polynomial
     */
    public Polynomial(int degree) {
        this.degree = degree;
        this.factors = new ArrayList<>();
        for (int i = 0; i <= degree; i++) {
            this.factors.add(0.0);//initializing factors as zeroes
        }
    }

    /**
     * Returns degree of polynomial
     *
     * @return degree of polynomial
     */
    public int getDegree() {
        return this.degree;
    }

    /**
     * Returns factors of polynomial
     *
     * @return array of factors of polynomial
     */
    public ArrayList<Double> getFactors() {
        return this.factors;
    }

    /**
     * Sets degree of polynomial
     *
     * @param degree - degree of polynomial
     */
    public void setDegree(int degree) {
        this.degree = degree;
    }

    /**
     * Sets factors of polynomial
     *
     * @param factors - array of factors of polynomial
     */
    public void setFactors(ArrayList<Double> factors) {
        this.factors.clear();
        for (double i : factors) {
            this.factors.add(i);
        }
    }

    /**
     * Calculates derivative of polynomial
     *
     * @return derived polynomial
     * @throws NotEnoughDataInArray - factors array is missing zeroes as factors
     * for Xes with the smallest exponents
     */
    public Polynomial derivative() throws NotEnoughDataInArray {
        if (this.degree != 0) {
            Polynomial derivedPolynomial = new Polynomial(this.degree - 1);
            int degreeCounter = this.degree;
            if (factors.size() < this.getDegree() + 1)//checks if factors array is missing zeroes as factors for Xes with the smallest exponents
            {
                throw new NotEnoughDataInArray("No data for Xes with the smallest exponents in factors array");
            } else {
                for (int i = 0; i <= derivedPolynomial.degree; i++) {
                    derivedPolynomial.factors.set(i, this.factors.get(i) * degreeCounter);
                    degreeCounter--;
                }
            }

            return derivedPolynomial;
        }
        Polynomial derivedPolynomial = new Polynomial(0);
        return derivedPolynomial;
    }

    @Override
    public String toString() {
        String result = "";
        if (this.degree == 0 || this.factors.stream().filter(f -> f != 0.0).count() == 0) {
            result = "0.0";
        } else {
            for (int i = 0; i < this.factors.size(); i++) {

                if (i != this.factors.size() - 1) {
                    if (this.factors.get(i) != 0) {
                        result += (((i == 0) ? " " : ((this.factors.get(i) < 0)) ? " " : " +") + this.factors.get(i) + "*x^" + (this.factors.size() - i - 1));
                    }
                } else {
                    if (this.factors.get(i) != 0) {
                        result += (((this.factors.get(i) < 0) ? " " : " +") + this.factors.get(i));
                    }
                }

            }
        }
        return result;
    }

}
