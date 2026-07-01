# Cronoanálise Industrial

Projeto Android nativo em Kotlin para monitoramento de linha viva e relatório estatístico OEE.

## Atende ao enunciado

- Duas Activities: monitoramento e relatório.
- Interface com ConstraintLayout e Material Design.
- Captura do tempo inicial com `System.currentTimeMillis()`.
- Campos de operador e linha são bloqueados após iniciar.
- Botão Registrar Peça começa desabilitado e é liberado após iniciar.
- Contador de peças em tempo real.
- Cálculo de tempo total e takt time.
- Envio dos dados por Intent.
- Relatório com operador e linha em caixa alta, peças inteiras, tempo em minutos/segundos e takt time com duas casas decimais.
- Separação em pacotes `ui`, `model` e `viewmodel`.

## Commits sugeridos

1. `chore: criar estrutura inicial do projeto Android`
2. `feat: implementar tela de monitoramento de produção`
3. `feat: adicionar ViewModel de controle da cronoanálise`
4. `feat: implementar relatório estatístico OEE`
5. `style: ajustar layout conforme protótipo visual`
6. `docs: adicionar instruções de execução do projeto`
