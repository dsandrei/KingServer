Design considerations for the SessionHelper and ScoreHelper:

1. Session key generation, session cleanup:
	- UUID.randomUUID() is thread-safe, however researching its complexity revealed that it is a performance bottleneck in some programs
	- the concurrent use of the same java.util.Random instance across threads may encounter contention and consequent poor performance
	- java.util.Random uses a linear congruential pseudo-random number generator, which does not guarantee too big of a period
	- an implementation of the Mersenne Twister algorithm was used, with a local source for ThreadLocalRandom that uses the Mersenne twister for number generation
	- Timer was considered, however for optimized concurrency the ScheduledThreadPoolExecutor insures a constant load instead of using an iterative cleanup task that would hog resources when called