/*  
    Copyright 2015 University of Southampton
    
    This file is part of JSIT.

    JSIT is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    JSIT is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with JSIT.  If not, see <http://www.gnu.org/licenses/>.
 */
package uk.ac.soton.simulation.jsit.core;

import java.io.Serializable;

import org.slf4j.*;

/**
 * JSIT abstract representation of all continuous probability distributions.
 * 
 * @author Stuart Rossiter
 * @since 0.1
 */    
public abstract class DistributionContinuous
                extends Distribution implements ContinuousSampler, Serializable {

    // ************************ Static Fields *****************************************

    private static final Logger logger
            = LoggerFactory.getLogger(DistributionContinuous.class);

    private static final long serialVersionUID = 1L;

    // ************************ Constructors ********************************************

    DistributionContinuous() {

        super();

    }


    // ************************* Public Methods *************************************

    /*
     * Sample a double; distribution must have been registered (sample mode set)
     * first
     */
    @Override
    public double sampleDouble() {

        AbstractStochasticAccessInfo accessor = getAccessInfo();
        if (accessor == null) {
            throw new IllegalStateException("Stochastic item not added to (registered via) an accessor");
        }
        Sampler.SampleMode mode = accessor.getSampleMode();
        assert mode != null;
        double sample = sampleDoubleByMode();
        if (logger.isTraceEnabled()) {
            logger.trace(accessor.getFullID() + " (Mode " + mode + "): sampled "
                    + sample + " from " + toString());
        }

        return sample;

    }

    /*
     * Force concrete subclasses to override toString()
     */
    @Override
    public abstract String toString();


    // ******************* Protected/Package-Access Methods *************************

    /*
     * 'Internal' abstract method to sample a double using the sample mode
     */
    protected abstract double sampleDoubleByMode(); 

}
