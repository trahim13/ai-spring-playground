# Ollama — Local LLM Platform

**Ollama** is a platform for running Large Language Models (LLMs) such as LLaMA, Mistral, Gemma, and others locally — without relying on cloud APIs.

- 🌐 Official website: [https://ollama.com](https://ollama.com)
- 🧪 Local API endpoint: [http://localhost:11434](http://localhost:11434)
- 📚 Available models: [https://ollama.com/library](https://ollama.com/library)

---

## 🚀 Run Ollama in Docker

```bash
docker run -d --name ollama -p 11434:11434 ollama/ollama
```

##  🛠️ Basic Commands (inside container)
#### Open interactive session in container
```bash
docker exec -it ollama bash
```
#### Show available Ollama commands
```bash
ollama help
```
#### Download a model
```bash
ollama pull llama3
```
#### List installed models
```bash
ollama list
```
#### Remove a model
```bash
ollama rm llama3
```
#### Show model information
```bash
ollama show llama3
```
#### Run a model interactively
```bash
ollama run llama3
```

## 📡 Make a Request to Ollama API
#### ✅ Linux / WSL / macOS (bash)

```bash
curl http://localhost:11434/api/generate \
  -H "Content-Type: application/json" \
  -d '{
    "model": "llama3",
    "prompt": "Tell me a joke about Java",
    "stream": false
  }'

```

#### ✅ Windows Command Prompt (cmd.exe)

```bash
curl http://localhost:11434/api/generate -H "Content-Type: application/json" -d "{ \"model\": \"llama3\", \"prompt\": \"Tell me a joke about Java\", \"stream\": false }"
```

#### ✅ Response
A successful response will look like this:

```json
{
  "model":"llama3",
  "created_at":"2025-07-09T10:45:31.999153155Z",
  "response":"A Java joke! Here's one:\n\nWhy did the Java program go to therapy?\n\nBecause it had a \"null\" point in its life!\n\nHope that one compiled successfully and made you smile!",
  "done":true,
  "done_reason":"stop",
    323,
    1903,
    499,
    15648,
    0
  ],
  "total_duration":2640715598,
  "load_duration":10969314,
  "prompt_eval_count":16,
  "prompt_eval_duration":134256077,
  "eval_count":40,
  "eval_duration":2494778395
}
```

---
# OpenAI — Cloud-Based LLM Platform

**OpenAI** provides powerful Large Language Models (LLMs) such as GPT-4, GPT-4o, and GPT-3.5 via a cloud API.  
You can interact with these models securely using an **API key** and HTTP requests — no local installation required.

- 🌐 Platform: [https://platform.openai.com](https://platform.openai.com)
- 🔑 API Key Management: [https://platform.openai.com/account/api-keys](https://platform.openai.com/account/api-keys)
- 📚 API Reference: [https://platform.openai.com/docs/api-reference](https://platform.openai.com/docs/api-reference)
- 📚 List of available models: [https://platform.openai.com/docs/models](https://platform.openai.com/docs/models)
- 📚 Compare models: [https://platform.openai.com/docs/models/compare](https://platform.openai.com/docs/models/compare)


---

## 🔐 How to Get and Use OpenAI API Key

This guide explains how to:

1. ✅ Obtain your OpenAI API key
2. ⚙️ Set it as an environment variable (Windows, macOS, Linux)
3. 🧪 Use it in an actual HTTP request

### 1. 🧾 Get API Key

1. Go to the [OpenAI API dashboard](https://platform.openai.com/account/api-keys)
2. Sign in with your account
3. Click **"Create new secret key"**
4. Copy and store the key securely (you won’t be able to see it again)

Example format: sk-XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


---

### 2. 🧩 Set API Key as Environment Variable

Example: `OPENAI_API_KEY` or `SPRING_AI_OPENAI_API_KEY`.

#### ✅ Linux / macOS / WSL

```bash
export OPENAI_API_KEY="sk-XXXXXXXXXXXXXXXXXXXXXXXX"
```

```bash
export SPRING_AI_OPENAI_API_KEY="sk-XXXXXXXXXXXXXXXXXXXXXXXX"
```


#### ✅ Windows Command Prompt (cmd.exe)

```bash
set OPENAI_API_KEY=sk-XXXXXXXXXXXXXXXXXXXXXXXX
```

```bash
set SPRING_AI_OPENAI_API_KEY=sk-XXXXXXXXXXXXXXXXXXXXXXXX
```


## 📡 Make a Request to OpenAI

This example sends a prompt to the OpenAI `gpt-4.1-mini` model using the Chat Completions API.  
It uses an API key stored in the `SPRING_AI_OPENAI_API_KEY` environment variable.

---


#### ✅ Linux / WSL / macOS (bash)

```bash
curl https://api.openai.com/v1/chat/completions \
  -H "Authorization: Bearer $SPRING_AI_OPENAI_API_KEY" \
  -H "Content-Type: application/json" \
  -d '{
    "model": "gpt-4.1-mini",
    "messages": [
      { "role": "user", "content": "Tell me a joke about Java" }
    ]
  }'
```

#### ✅ Windows Command Prompt (cmd.exe)

```bash
curl https://api.openai.com/v1/chat/completions -H "Authorization: Bearer %SPRING_AI_OPENAI_API_KEY%" -H "Content-Type: application/json" -d "{ \"model\": \"gpt-4.1-mini\", \"messages\": [ { \"role\": \"user\", \"content\": \"Tell me a joke about Java\" } ]}"
```


#### ✅ Response
A successful response will look like this:

```json
{
  "id":"chatcmpl-BrNO5GNWMmMBEzPFkP0cCkKVkVss7",
  "object":"chat.completion",
  "created":1752060985,
  "model":"gpt-4.1-mini-2025-04-14",
  "choices":[
    {
      "index":0,
      "message":{
        "role":"assistant",
        "content":"Sure! Here's a Java joke for you:\n\nWhy do Java developers wear glasses?  \nBecause they don't C#!",
        "refusal":null,
        "annotations":[

        ]
      },
      "logprobs":null,
      "finish_reason":"stop"
    }
  ],
  "usage":{
    "prompt_tokens":13,
    "completion_tokens":22,
    "total_tokens":35,
    "prompt_tokens_details":{
      "cached_tokens":0,
      "audio_tokens":0
    },
    "completion_tokens_details":{
      "reasoning_tokens":0,
      "audio_tokens":0,
      "accepted_prediction_tokens":0,
      "rejected_prediction_tokens":0
    }
  },
  "service_tier":"default",
  "system_fingerprint":"fp_658b958c37"
}
```

## 🧪 Example: Call `/chat/ai` Endpoint via curl

This controller accepts two parameters:
- `input` — the user message (required)
- `engine` — the engine to use (`openai` or `ollama`, optional, default is `ollama`)

The response will contain the assistant’s reply text.

---
## ✅ Linux / macOS / WSL (bash)

### 🔸 Use default engine (ollama):

```bash
curl "http://localhost:8080/chat/ai?input=Tell%20me%20a%20joke%20about%20Java"
```

### 🔸 Use OpenAI engine:
```bash
curl "http://localhost:8080/chat/ai?input=Tell%20me%20a%20joke%20about%20Java&engine=openai"
```

#### ✅ Windows Command Prompt (cmd.exe)

### 🔸 Use default engine (ollama):

```bash
curl "http://localhost:8080/chat/ai?input=Tell%20me%20a%20joke%20about%20Java"
```

### 🔸 Use OpenAI engine:
```bash
curl "http://localhost:8080/chat/ai?input=Tell%20me%20a%20joke%20about%20Java&engine=openai"
```


