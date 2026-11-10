# =====================================================================
# TRABAJO DE ALGORITMOS - ESTRUCTURA DE DATOS
# Estudiante: Ingeniería de Sistemas
# =====================================================================

print("==========================================")
print("       PUNTO A: LISTA SIMPLEMENTE LIGADA  ")
print("==========================================")

class NodoSimple:
    def __init__(self, dato):
        self.dato = dato
        self.siguiente = None

class ListaSimplementeLigada:
    def __init__(self):
        self.cabeza = None

    def insertar_al_final(self, dato):
        nuevo_nodo = NodoSimple(dato)
        if not self.cabeza:
            self.cabeza = nuevo_nodo
        else:
            actual = self.cabeza
            while actual.siguiente:
                actual = actual.siguiente
            actual.siguiente = nuevo_nodo

    def imprimir_lista(self):
        actual = self.cabeza
        while actual:
            print(f"[ {actual.dato} ]", end=" -> ")
            actual = actual.siguiente
        print("None")

# Capturar el número de 4 dígitos de forma interactiva
numero_str = input("Ingrese un número entero de 4 dígitos para el Punto A: ")

if len(numero_str) == 4 and numero_str.isdigit():
    lista_a = ListaSimplementeLigada()
    for digito in numero_str:
        lista_a.insertar_al_final(int(digito))
    
    print("Lista simplemente ligada creada con los dígitos:")
    lista_a.imprimir_lista()
else:
    print("Error: Debe ingresar exactamente un número entero de 4 dígitos.")


print("\n" + "="*42)
print("      PUNTO B: LISTA DOBLEMENTE LIGADA    ")
print("==========================================")

class NodoDoble:
    def __init__(self, dato):
        self.dato = dato
        self.siguiente = None
        self.anterior = None

class ListaDoblementeLigada:
    def __init__(self):
        self.cabeza = None
        self.cola = None

    def insertar_al_final(self, dato):
        nuevo_nodo = NodoDoble(dato)
        if not self.cabeza:
            self.cabeza = nuevo_nodo
            self.cola = nuevo_nodo
        else:
            self.cola.siguiente = nuevo_nodo
            nuevo_nodo.anterior = self.cola
            self.cola = nuevo_nodo

    def imprimir_lista(self):
        actual = self.cabeza
        print("None", end=" <-> ")
        while actual:
            print(f"[ {actual.dato} ]", end=" <-> ")
            actual = actual.siguiente
        print("None")

def es_primo(n):
    if n <= 1:
        return False
    if n == 2:
        return True
    if n % 2 == 0:
        return False
    i = 3
    while i * i <= n:
        if n % i == 0:
            return False
        i += 2
    return True

# Leer N números enteros de forma interactiva como lo pide el profesor
N = int(input("Ingrese la cantidad de números enteros (N) a evaluar para el Punto B: "))
lista_b = ListaDoblementeLigada()

for i in range(N):
    num = int(input(f"Ingrese el número entero {i+1}: "))
    if es_primo(num):
        lista_b.insertar_al_final(num)

print("\nLista doblemente ligada con los números primos encontrados:")
lista_b.imprimir_lista()