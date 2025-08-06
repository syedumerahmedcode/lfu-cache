# lfu-cache

## Table of content

- [Introduction](#introduction)
- [Explanation](#explanation)
- [Complexity Analysis](#complexity-analysis)
- [Edge Cases](#edge-cases)
- [Technologies Used](#technologies-used)
- [Commands](#commands)
- [Contribution](#contribution)
- [Contact Information](#contact-information)

## Introduction

to be written
For Example

```java
Input

inputToCheck = [-2, 1, -3, 4, -1, 2, 1, -5, 4];

Output

result: 6, which corresponds to the subarray [4, -1, 2, 1].
```

## Explanation

to be written - describe the method in the service class

### Initialization:

We start with two variables:

`maximumSoFar:` This holds the maximum sum found so far. We initialize it to the first element of the array.

`maximumEndingHere:` This holds tthe maximum sum of the subarray that ends at the current index. We initialize it to the first element of the array as well.

### Iterate Through the Array:

For each element in the array (starting from the second element), we:

- update `maximumEndingHere` using `maximumEndingHere = max(currentElement, maximumEndingHere + currentElement)`. This step checks whether to start a new subarray at the current element or to continue the existing subarray. Also, we

- Update `maximumSoFar` via `maximumSoFar = max(maximumSoFar, maximumEndingHere)`. This helps us in keeping track of the overall maximum sum found.

### Resetting :

If `maximumEndingHere` becomes negative, it indicates that the current subarray is not contributing positively to any future subarray. In this case, you can reset maxEndingHere to zero (or the next element).

### Return the Result:

After iterating through the array, `maximumSoFar` will contain the maximum sum of any contiguous subarray.

### Dry-run

Let's consider an example array: `[-2, 1, -3, 4, -1, 2, 1, -5, 4]`.

#### Final result

After processing all elements, `maximumSoFar` is 6, which corresponds to the subarray _[4, -1, 2, 1]_.

> [to be inserted](Please insert working link here)

## Complexity Analysis

### Time Complexity

**O(n)**: to be written

## Technologies Used

- Java 11

## Commands

To compile and test, please checkout the project on your local machine and run _main method_ in the Executor class.

## Contribution

Feature requests, issues, pull requests and questions are welcome.

## Contact Information

How to reach me? At [github specific gmail account](mailto:syedumerahmedcode@gmail.com?subject=[GitHub]%20Hello%20from%20Github).
