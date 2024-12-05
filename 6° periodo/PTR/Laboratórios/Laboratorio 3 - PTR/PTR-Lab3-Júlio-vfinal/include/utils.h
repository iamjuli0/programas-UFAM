#ifndef UTILS_H__
#define UTILS_H__

#define M_PI  3.14159265358979323846

#include <time.h>

static inline double timespec_diff(const struct timespec *start, const struct timespec *end) {
    struct timespec res, *result = &res;
    result->tv_sec = end->tv_sec - start->tv_sec;
    result->tv_nsec = end->tv_nsec - start->tv_nsec;

    // Handle nanoseconds wraparound
    if (result->tv_nsec < 0) {
        --result->tv_sec;
        result->tv_nsec += 1000000000L; // adjust to positive range
    }
    return (double)result->tv_sec + ((double)result->tv_nsec / 1000000000.0);
}

#endif //UTILS_H__