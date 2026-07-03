# Software Testing and QA Labs

This directory contains practical testing and quality assurance exercises completed as part of my Software Engineering coursework. These labs focus on hands-on experience with testing methodologies, automation, and quality assurance practices using Python and pytest.

## Overview

These labs demonstrate real-world software testing practices including:
- Unit testing with pytest
- Test-driven development (TDD)
- Code coverage analysis (statement and branch coverage)
- Automated testing frameworks
- Quality assurance best practices

## Topics Covered

- Unit Testing Fundamentals
- pytest Framework and Fixtures
- Test Cases and Assertions
- Mocking and Patching
- Code Coverage Analysis (Statement & Branch Coverage)
- Test-Driven Development (TDD)
- Integration Testing
- Performance Testing
- Test Automation
- Continuous Integration/Continuous Testing (CI/CD)

## Folder Structure

```
software_testing_and_QA_labs/
├── test_examples/          # Basic testing examples
├── unit_tests/             # Unit test cases
├── integration_tests/       # Integration test scenarios
├── fixtures/               # pytest fixtures and utilities
├── test_data/              # Test data and fixtures
├── test_reports/           # Coverage reports and test results
└── README.md               # You're here!
```

## Requirements

- Python 3.8+
- pytest
- pytest-cov (for code coverage)
- unittest (built-in)
- Optional: pytest-xdist (for parallel testing)

## Installation

```bash
# Clone the repository
git clone https://github.com/faustine-van/University-Projects.git
cd University-Projects/software_testing_and_QA_labs

# Create virtual environment
python3 -m venv venv
source venv/bin/activate  # On Windows: venv\Scripts\activate

# Install dependencies
pip install pytest pytest-cov pytest-xdist
```

## Running Tests

```bash
# Run all tests
pytest

# Run with verbose output
pytest -v

# Run specific test file
pytest test_examples/test_sample.py

# Run with code coverage
pytest --cov=. --cov-report=html

# Run with statement and branch coverage (advanced)
pytest --cov=falco_refill test_falco.py --cov-branch --cov-report=term-missing

# Run tests in parallel
pytest -n auto

# Run tests and show print statements
pytest -s
```

### Coverage Command Breakdown

The advanced coverage command demonstrates comprehensive testing:
```bash
pytest --cov=falco_refill test_falco.py --cov-branch --cov-report=term-missing
```

- `--cov=falco_refill` - Measure coverage for the `falco_refill` module
- `test_falco.py` - Run tests from this specific file
- `--cov-branch` - Include branch coverage analysis (not just statement coverage)
- `--cov-report=term-missing` - Display coverage report in terminal with missing lines highlighted

## Key Concepts Practiced

- **Assertions**: Validating expected vs. actual results
- **Test Fixtures**: Reusable test setup and teardown
- **Mocking**: Simulating external dependencies
- **Parametrized Testing**: Testing multiple input scenarios
- **Code Coverage**: Measuring test effectiveness
- **Statement Coverage**: Percentage of code statements executed by tests
- **Branch Coverage**: Percentage of conditional branches tested (if/else, loops, etc.)
- **Test Organization**: Structuring tests for maintainability
- **Edge Cases**: Testing boundary conditions
- **Error Handling**: Testing exception scenarios

## Example Test Structure

```python
import pytest

# Simple test case
def test_addition():
    assert 2 + 2 == 4

# Test with fixtures
@pytest.fixture
def sample_data():
    return [1, 2, 3, 4, 5]

def test_list_length(sample_data):
    assert len(sample_data) == 5

# Parametrized test
@pytest.mark.parametrize("input,expected", [
    (1, 2),
    (2, 4),
    (3, 6),
])
def test_double(input, expected):
    assert input * 2 == expected
```

## Use Cases

- Validate business logic functions
- Ensure code reliability before deployment
- Test edge cases and error conditions
- Measure code quality with coverage metrics
- Automate regression testing
- Support continuous integration pipelines
- Verify both success and failure paths in code

## Best Practices

- Write tests alongside code (TDD approach)
- Keep tests focused and independent
- Use descriptive test names
- Maintain good code coverage (aim for 80%+)
- Organize tests logically by functionality
- Use fixtures for reusable test setup
- Test both success and failure scenarios
- Keep tests fast and deterministic
- Track both statement and branch coverage
- Document test purposes and edge cases

## Coverage Types

### Statement Coverage
- Measures the percentage of executable statements that are executed by tests
- Ensures code lines are executed but doesn't verify all conditions
- Good starting point for coverage analysis
- **Learn more**: [Statement Coverage Testing - GeeksforGeeks](https://www.geeksforgeeks.org/software-testing/statement-coverage-testing/)

### Branch Coverage
- Measures the percentage of conditional branches (if/else, loops, switches) that are tested
- More comprehensive than statement coverage
- Ensures both true and false conditions are tested
- Catches logic errors in conditional statements
- **Learn more**: [Statement Coverage vs Branch Coverage - GraphApp](https://www.graphapp.ai/blog/statement-coverage-vs-branch-coverage-a-comprehensive-comparison)

## Resources

### Testing Frameworks & Tools
- [pytest Documentation](https://docs.pytest.org/)
- [Python unittest Documentation](https://docs.python.org/3/library/unittest.html)
- [Code Coverage Measurement](https://coverage.readthedocs.io/)

### Coverage Analysis
- [Statement Coverage vs Branch Coverage - Comprehensive Comparison](https://www.graphapp.ai/blog/statement-coverage-vs-branch-coverage-a-comprehensive-comparison)
- [Statement Coverage Testing - GeeksforGeeks](https://www.geeksforgeeks.org/software-testing/statement-coverage-testing/)

### Development Methodologies
- [Test-Driven Development Guide](https://en.wikipedia.org/wiki/Test-driven_development)
- [Software Testing Best Practices](https://www.geeksforgeeks.org/software-testing/)

---

> **Quality Assurance Note:** All code in this folder is tested, reviewed for clarity, and follows testing best practices.

> Feel free to fork, adapt, or extend these labs for your own learning or projects!

---

## Author

**[Muhayemariya Faustine](https://github.com/faustine-van)**

Software Engineer | QA Enthusiast | Exploring real-world scenarios through code

---

## License

This project is licensed under the [MIT License](LICENSE).
