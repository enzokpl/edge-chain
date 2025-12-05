# Edge Chain 🔗

[![](https://jitpack.io/v/enzokpl/edge-chain.svg)](https://jitpack.io/#enzokpl/edge-chain)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A lightweight, generic Java data structure for representing linear chains where **relationships (edges) carry semantic value**.

Standard Java `LinkedList` stores data in nodes. `EdgeChain` stores data in nodes **AND** strictly typed relationships between them.

## Features

* **Type Safety:** Uses Java Generics `<T, R>` to ensure data consistency.
* **Semantic Edges:** Define exactly how Node A is connected to Node B.
* **Validation Strategy:** Built-in support for validating the entire chain logic using functional interfaces.
* **Zero Dependencies:** Lightweight and requires only Java 11+.

## Installation

This library is distributed via **JitPack**.

### Maven

Add the JitPack repository to your build file:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>[https://jitpack.io](https://jitpack.io)</url>
    </repository>
</repositories>
```

Add the dependency:
```xml
<dependencies>
    <dependency>
        <groupId>com.github.enzokpl</groupId>
        <artifactId>edge-chain</artifactId>
        <version>v1.0.0</version>
    </dependency>
</dependencies>
```

### Gradle

```groovy
repositories { 
    maven { url '[https://jitpack.io](https://jitpack.io)' }
}

dependencies {
    implementation 'com.github.enzokpl:edge-chain:v1.0.0'
}
```

### Usage Examples

1. Basic Logic Chain: Representing `Object 1 != Object 2 > Object 3`

```java
import io.github.enzokpl.edgechain.Chain;
import io.github.enzokpl.edgechain.LinkedChain;

public class Example {
    public static void main(String[] args) {
        // T = String (Data), R = String (Operator)
        Chain<String, String> chain = new LinkedChain<>();

        chain.addFirst("Object 1");
        chain.addNext("!=", "Object 2");
        chain.addNext(">", "Object 3");

        System.out.println(chain); 
        // Output: Object 1 --[!=]--> Object 2 --[>]--> Object 3 
    }
}
```
2. Validating Business Rules: You can validate if the chain makes sense logically using isValid().

```java
Chain<Integer, String> mathChain = new LinkedChain<>();
mathChain.addFirst(10);
mathChain.addNext(">", 5); // True
mathChain.addNext("<", 8); // True

// Define the logic for the operators
boolean result = mathChain.isValid((left, op, right) -> {
    switch (op) {
        case ">": return left > right;
        case "<": return left < right;
        case "=": return left.equals(right);
        default: return false;
    }
});

System.out.println("Is Valid? " + result); // Output: true
```

## License
This project is licensed under the MIT License - see the LICENSE file for details.
