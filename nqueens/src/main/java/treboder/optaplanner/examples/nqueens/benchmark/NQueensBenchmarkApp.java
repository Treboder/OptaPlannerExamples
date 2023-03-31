package treboder.optaplanner.examples.nqueens.benchmark;

public class NQueensBenchmarkApp extends CommonBenchmarkApp {

    public static void main(String[] args) {
        new NQueensBenchmarkApp().buildAndBenchmark(args);
    }

    public NQueensBenchmarkApp() {
        super(
                new ArgOption("default", "benchmark/nqueensBenchmarkConfig.xml")
                // ToDo: figure out how to use scoreDirector and stepLimit below
                // ,new ArgOption("scoreDirector", "benchmark/nqueensScoreDirectorBenchmarkConfig.xml")
                // ,new ArgOption("stepLimit", "benchmark/nqueensStepLimitBenchmarkConfig.xml")
        );
    }

}
