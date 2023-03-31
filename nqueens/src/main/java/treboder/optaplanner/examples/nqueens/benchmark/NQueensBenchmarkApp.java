package treboder.optaplanner.examples.nqueens.benchmark;

public class NQueensBenchmarkApp extends CommonBenchmarkApp {

    public static void main(String[] args) {
        new NQueensBenchmarkApp().buildAndBenchmark(args);
    }

    public NQueensBenchmarkApp() {
        super(
                new ArgOption("default", "benchmark/nqueensBenchmarkConfig.xml")
                //,new ArgOption("stepLimit", "benchmark/nqueensStepLimitBenchmarkConfig.xml")
                //,new ArgOption("scoreDirector", "benchmark/nqueensScoreDirectorBenchmarkConfig.xml")
        );
    }

}
