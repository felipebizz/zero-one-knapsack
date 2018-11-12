package com.mobiquityinc;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;
import org.apache.log4j.Logger;

import java.util.Optional;

/**
 * This class is the start point of the application, and will process the passed parameters.
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
public class Main {

    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                throw new APIException("You should pass the file path");
            }

            final String pack = Packer.pack(args[0]);

            logger.info(String.format("Output:\r\n%s", pack));
        } catch (Exception e) {
            logger.error(Optional.of(e.getMessage()).orElse("Error occurred in executing the packer project!"));
        }
    }
}
