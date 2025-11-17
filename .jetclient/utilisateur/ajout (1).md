```toml
name = 'ajout (1)'
method = 'POST'
url = 'http://localhost:8080/utilisateur'
sortWeight = 3000000
id = '4ac0d006-cf62-40d0-b1c8-e19a0be02887'

[body]
type = 'JSON'
raw = '''
{
  "nom" : "Toto",
  "prenom" : "Titi",
  "organisations" : [
    {
      "id" : 1
    },
    {
       "id" : 3
    }
  ]
}'''
```
