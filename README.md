# TD2 – Gestion des exceptions en Java : virements bancaires

Avancement : **Parties 1 et 2 terminées** (validation du virement + exceptions métier).


## 1. Arborescence du projet

Hiérarchie des exceptions :

```
Exception
 └── BankingException
      ├── InsufficientFundsException
      ├── TransferLimitExceededException
      └── InvalidAccountException
```

---

## 2. Explication des déclarations

### 2.1 `private static final long serialVersionUID = 1L;`

Présente dans chaque classe d'exception.
- `serialVersionUID` est le **numéro de version** de la classe. À la relecture, Java compare
  ce numéro avec celui de la classe chargée. S'ils sont différents, il refuse de reconstruire
  l'objet (`InvalidClassException`).

### 2.2 Les spécificateurs de format `%s`, `%.2f` (avec `String.format`)

| Spécificateur | Signification | Exemple |
|---|---|---|
| `%s` | insère une valeur sous forme de **texte** (String, ou `toString()` d'un objet) | `"FR-001"` → `FR-001` |
| `%.2f` | insère un nombre décimal (`double`/`float`) avec **exactement 2 chiffres après la virgule** (arrondi) | `2000.0` → `2000,00` |
| `%d` | insère un nombre entier (`int`/`long`) | `42` → `42` |
| `%n` | retour à la ligne (adapté au système) | |



### 2.3 Le paramètre `cause` : conserver l'exception d'origine

Dans [BankingException.java](src/bank/exception/BankingException.java) :

```java
protected BankingException(String message, Throwable cause) {
    super(message, cause);
}
```
- `cause` est **l'exception d'origine**, celle qui a provoqué notre exception métier.
- Sans `cause`, l'information sur la vraie panne serait **perdue** : impossible de savoir
 