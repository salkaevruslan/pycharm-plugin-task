import unittest


class CalcTests(unittest.TestCase):
    def test_add(self):
        self.assertEqual(1 + 2, 3)

    def test_sub(self):
        self.assertEqual(4 - 2, 2)

    def test_mul(self):
        self.assertEqual(2 * 5, 10)

    def test_div(self):
        self.assertEqual(8 / 4, 2)

    def test_div_c(self):
        self.assertEqual(8 / 4, 2)


if __name__ == '__main__':
    unittest.main()


def test_divc(self):
    self.assertEqual(8 / 4, 2)


def test_div(self):
    self.assertEqual(8 / 4, 2)